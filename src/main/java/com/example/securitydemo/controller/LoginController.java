package com.example.securitydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
