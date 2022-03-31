package com.example.securitydemo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author chenxinbao
 * @version 1.0
 * @date 2022/3/29 8:48
 */
@Controller
public class LoginController {

    @GetMapping("/showLogin")
    public String showLogin(){
        return "login";
    }

    @PreAuthorize("hasRole('admin')")
    @RequestMapping("/index")
    public String indexHome(){
        return "index";
    }

    @RequestMapping("/errorPage")
    public String errorPage(){
        return "error";
    }


    @RequestMapping("/role")
    public String role(){
        return "role";
    }

//    @Secured("admin")
    @RequestMapping("/home")
    public String home(){
        return "home";
    }
}
