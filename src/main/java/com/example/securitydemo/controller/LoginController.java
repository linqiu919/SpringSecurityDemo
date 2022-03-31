package com.example.securitydemo.controller;

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

    @RequestMapping("/index")
    public String indexHome(){
        return "index";
    }

    @RequestMapping("/errorPage")
    public String errorPage(){
        return "error";
    }
}
