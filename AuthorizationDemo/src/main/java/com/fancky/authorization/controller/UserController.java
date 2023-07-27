package com.fancky.authorization.controller;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
客户单调用在FrameworkEndpoint 中调用
 */
@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private TokenStore tokenStore;

//    postman访问不了,貌似只能访问oauth 的接口
    @GetMapping("/user/getUser")
    @ResponseBody
    public Object getUser(Authentication authentication, HttpServletRequest request) {


        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

        if (authentication1.isAuthenticated()) {
            //        获取当前的登录信息。
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetails userDetails = (UserDetails) principal;


        }

        String header = request.getHeader("Authorization");
        String token = StrUtil.subAfter(header, "bearer ", false);
        return Jwts.parser()
                .setSigningKey("test_key".getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
    }

    @RequestMapping("/user/login")
    // @ResponseBody// 就是返回字符串了
    public String index() {
        //返回值给页面
        return "login/index";
    }

    @RequestMapping("/loginSuccess")
    // @ResponseBody// 就是返回字符串了
    public String loginSuccess() {
        //返回值给页面
        return "index";
    }



}
