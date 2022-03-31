package com.example.securitydemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

import java.util.Collection;

/**
 * @author chenxinbao
 * @version 1.0
 * @date 2022/3/29 8:48
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAccessDeinedHandler accessDeinedHandler;

    @Autowired
    private CorsFilter corsFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(bCryptPasswordEncoder().encode("123456"))
                .roles("admin", "superAdmin")
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("system,system:user,system:user:list"))
                .and()
                .withUser("common")
                .password(bCryptPasswordEncoder().encode("123456"))
                .roles("common")
                .authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("good,good:cate,good:cate:list"));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /**
         * 关闭csrf，默认是开启的
         */
        http.csrf().disable();

        /**
         * 表单登录相关配置
         */
        http.formLogin()
                // 当http请求的url是/login时，进行自定义登录逻辑
                .loginProcessingUrl("/login")
                // 自定义登录的前端控制器
                .loginPage("/showLogin")
                // 登录成功跳转连接
//                .successForwardUrl("/index")
                // 设置登录失败的跳转连接
//                .failureForwardUrl("/errorPage")
                // 通过successHandler处理登录失败之后的逻辑
                .successHandler((request, response, authentication) -> {
                    System.out.println("登录成功，页面即将跳转。。。。");
                    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                    authorities.forEach(System.out::println);
                    response.sendRedirect("/home");
                })
                // 通过failtureHandler处理登录失败之后的逻辑
                .failureHandler((request, response, exception) -> {
                    exception.printStackTrace();
                    System.out.println("登录失败，页面即将跳转到默认失败页面。。。");
                    response.sendRedirect("/errorPage");
                })
                .and()
                // 403权限不足异常使用自定义处理器
                .exceptionHandling().accessDeniedHandler(accessDeinedHandler);

        /**
         *  用户退出登录处理配置
         */
        http.logout().logoutUrl("/loginout")
                // 用户退出成功后跳转链接
                .logoutSuccessUrl("/showLogin")
                // 自定义退出逻辑，可以处理session，cookie等信息
                .addLogoutHandler((request, response, authentication) -> {
                    System.out.println("-------------------------");
                    System.out.println("成功退出登录！");
                })
                // 是否清除认证状态
                .clearAuthentication(true)
                // 是否销毁HttpSession对象，默认为true
                .invalidateHttpSession(true);

        /**
         * http请求登录认证配置
         */
        http.authorizeRequests()
                // 允许登录页面匿名访问
                .antMatchers(HttpMethod.GET, "/showLogin", "/errorPage").anonymous()
                // 用户具有admin角色时允许访问/role
                .antMatchers("/role").hasRole("admin")
                // 用户具有system:user权限时允许访问/role
                .antMatchers("/role").hasAuthority("system:user")
                // 对所有的其他请求都进行登录认证
                .anyRequest().authenticated();
                // TODO 添加跨域配置

    }

    @Override
    public void configure(WebSecurity web) {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/**/*.js", "/lang/*.json", "/**/*.css", "/**/*.js", "/**/*.map", "/**/*.html", "/**/*.png");
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
