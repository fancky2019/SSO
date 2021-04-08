package com.fancky.authorization.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable();////关跨域保护
        http.authorizeRequests()
                .antMatchers("/oauth/**", "/login/**", "/login","/logout/**", "/authentication/form","/login**","/oauth/authorize")//允许认证、登录、登出
                .permitAll()
                .anyRequest()
                .authenticated()////所有请求都需要通过认证
                .and()
                .formLogin()
                .loginPage("/user/login")// 登录页面
                .loginProcessingUrl("/authentication/form")
                .permitAll();;
//        http.httpBasic().disable();


//        http.formLogin()// 表单登录  来身份认证
//                .loginPage("/user/login")// 登录页面
//                .loginProcessingUrl("/authentication/form")// 自定义登录路径
//                .and()
//                .authorizeRequests()// 对请求授权
//                // error  127.0.0.1 将您重定向的次数过多
//                .antMatchers("/oauth/**","/user/login","/login/**", "/login","/logout/**", "/authentication/form")//允许认证、登录、登出
//                .permitAll()// 这些页面不需要身份认证,其他请求需要认证
//                .anyRequest() // 任何请求
//                .authenticated()//; // 都需要身份认证
//                .and()
//                .csrf().disable();// 禁用跨站攻击



    }
}

