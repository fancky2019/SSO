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
                .disable()////关跨域保护
                .authorizeRequests()
                .antMatchers("/oauth/**", "/login/**", "/logout/**")//允许认证、登录、登出
                .permitAll()
                .anyRequest()
                .authenticated()////所有请求都需要通过认证
                .and()
                .formLogin()
                .permitAll();

        // 表单登录
        http.formLogin()
                .loginPage("/user/login")// 登录页面
                .loginProcessingUrl("/authentication/form");//自定义表单请求路径
        http.httpBasic().disable();

    }
}

