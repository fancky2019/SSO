package com.fancky.authorizationclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * 资源服务器
 */
@SpringBootApplication
@EnableOAuth2Sso
public class AuthorizationClientApplication {

    /*
    https://jwt.io/#debugger-io
     */


    /*
    tokenValue
    https://jwt.io/#debugger-io
    {
  "alg": "HS256",
  "typ": "JWT"
}
    jwt的唯一身份标识，主要用来作为一次性token，从而回避重放攻击
    {
  "exp": 1617701727,
  "user_name": "admin",
  "authorities": [
    "ROLE_ADMIN"
  ],
  "jti": "2210b9c7-dbc4-425a-9986-0b30a5e7cc51",
  "client_id": "client-id",
  "scope": [
    "all"
  ]
}
     */

    /*
      通过post  方式获取 //http://localhost:9001/oauth/token?client_id=client-id&client_secret=client_secret&grant_type=password&username=admin&password=123
      body下form-data或x-www-form-urlencoded中填写
      填写路径中的参数
     */

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationClientApplication.class, args);
    }

}
