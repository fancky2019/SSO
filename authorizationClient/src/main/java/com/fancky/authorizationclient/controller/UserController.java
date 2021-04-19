package com.fancky.authorizationclient.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    /*
     Using generated security password: 0af75d31-f9f6-4e93-b6a3-67e6c03bb447
     框架登录中心默认生成用户名密码：user 0af75d31-f9f6-4e93-b6a3-67e6c03bb447
     */

    /*
      没有权限会报错：Whitelabel Error Page
     */


    @GetMapping("/getUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Object getUser(Authentication authentication) {

        return authentication;
    }

    @GetMapping("/getCommonData")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Object getCommonData(Authentication authentication) {

        return authentication;
    }

    @GetMapping("")
    public void user(Authentication authentication) {
        int m = 0;
    }

}