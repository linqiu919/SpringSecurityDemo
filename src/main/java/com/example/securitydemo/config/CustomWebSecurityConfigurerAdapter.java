package com.example.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author chenxinbao
 * @version 1.0
 * @date 2022/3/29 8:48
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@Order(90)
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(bCryptPasswordEncoder().encode("123456"))
                .roles("admin", "superAdmin")
                .and()
                .withUser("common")
                .password(bCryptPasswordEncoder().encode("123456"))
                .roles("common");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 表单登录相关配置
         */
        http.formLogin()
                // 当http请求的url是/login时，进行自定义登录逻辑
                .loginProcessingUrl("/login")
                // 自定义登录的前端控制器
                .loginPage("/showLogin");

        /**
         * http请求是否需要登录认证
         */
        http.authorizeRequests()
                .antMatchers("/showLogin").anonymous() // 允许登录页面匿名访问
                .anyRequest() // 对所有的其他请求都进行登录认证
                .authenticated();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("**/css/**","**/image/**");
    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
