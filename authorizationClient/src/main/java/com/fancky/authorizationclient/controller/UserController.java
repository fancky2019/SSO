package com.fancky.authorizationclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
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
    //基于角色的，实际基于角色动态从数据库获取加白名单方式
//    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Object getUser(Authentication authentication) {

        return authentication;
    }

    /**
     * ResourceServerConfig 配置类中配置白名单
     *没有加入白名单，需要认证权限
     */
    @GetMapping("/getCommonData")
//    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public Object getCommonData(Authentication authentication) {

        return authentication;
    }

    /**
     *需要认证权限
     */
    @GetMapping("")
    public void user(Authentication authentication) {
        int m = 0;
    }

    /**
     * ResourceServerConfig 配置类中配置白名单
     *加入白名单，不需要权限
     */
    @GetMapping("/permitAccess")
    public String permitAccess(Authentication authentication) {

        return "permitAccess";
    }
//

}