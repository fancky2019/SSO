package com.fancky.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication
@EnableAuthorizationServer
public class AuthorizationApplication {

//    参考链接
//    https://blog.csdn.net/qq15035899256/article/details/129541483?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1-129541483-blog-127025830.235^v38^pc_relevant_sort_base1&spm=1001.2101.3001.4242.2&utm_relevant_index=2

//  会话保存  spring-session-data-redis
    /*
     授权码模式：
     1：http://localhost:9001/oauth/authorize?client_id=client_id1&response_type=token


     授权码模式登录流程设计：
     1、访问资源未登录，跳转到登录页。
     2、登录成功之后返回给前段授权码，前段返回首页或者重定向上次请求页面
     3、前段拿到授权码之后获取token
     4、获取到touken之后判断是或否继续请求。
     */


//    使用jwt 就别设计退出功能，jwt是无状态去中心化的


   // token 令牌保存方式：
   // jwt:不需要额外配置数据库，
   // db ：需要配置数据库
   // redis：需要配置redis,但是可以做登出功能设计，redis保存登录的key的白名单，登出时候直接删除token
//            其他客户端就在访问就需要登录，jwt 方式token信息保存在jwt中，只要token不过期，token就有效。
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }

}
