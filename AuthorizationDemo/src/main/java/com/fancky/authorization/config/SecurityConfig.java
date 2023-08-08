package com.fancky.authorization.config;

import com.fancky.authorization.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
// 注解需要使用@EnableWebFluxSecurity而非@EnableWebSecurity,因为SpringCloud Gateway基于WebFlux
//@EnableWebFluxSecurity
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


    /**
     * 将 check_token 暴露出去，否则资源服务器访问时报403错误
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        //暴露不需要权限的url
        web.ignoring().antMatchers("/oauth/check_token");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http.csrf()
//                .disable();////关跨域保护
//        http.authorizeRequests()
//                .antMatchers("/oauth/**", "/login","/login/**","/logout/**", "/authentication/form","/oauth/authorize","/"
//                       ,"/user/login")//允许认证、登录、登出
//                .permitAll()
//                .anyRequest()
//                .authenticated()////所有请求都需要通过认证
//                .and()
//                .formLogin()
//                .loginPage("/user/login")// 登录页面
//                .loginProcessingUrl("/authentication/form")
////                .defaultSuccessUrl("/",false)
//                .permitAll();;
//        http.httpBasic().disable();


        http   // 配置登录页并允许访问
                .formLogin()
                .loginPage("/user/login")// 自定义登录页面，不用原生的
                .loginProcessingUrl("/authentication/form")
                //此处配置无效，取的是数据库oauth_client_details表里的配置
               // .defaultSuccessUrl("https://www.baidu.com/", false)
                .permitAll()
                // 配置Basic登录
                //.and().httpBasic()
                // 不用配置退出，RevokeTokenEndpoint写了退出逻辑。
                // 退出成功之后由前端重定向到sso登录页， 配置登出页面 默认为 /logout
              //  .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                .and().authorizeRequests().antMatchers("/oauth/**", "/login/**", "/authentication/form", "/user/login", "/logout/**").permitAll()
                // 其余所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                // 关闭跨域保护;
                .and().csrf().disable();

    }
}

