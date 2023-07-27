package com.fancky.authorizationclient.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.env.EnvironmentUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;


@Configuration
@EnableResourceServer // 加了报错Full authentication is required to access this resource
//激活方法上的PreAuthorize注解
//@EnableGlobalMethodSecurity(prePostEnabled = true)//
@Order(101)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory ;

    public TokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory) ;
    }


    /*
    要和oauth_client_details表里该条配置的resource_ids一致
     */
    private static final String DEMO_RESOURCE_ID = "project_api";


    //region jwt tokenStore
    @Autowired
    private TokenStore jwtTokenStore;
    @Bean
    public TokenStore jwtTokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        //注：要和授权服务器jwt的SigningKey保持一致
        accessTokenConverter.setSigningKey("JWT_Sign_Key");
        accessTokenConverter.setVerifierKey("JWT_Sign_Key");
        return accessTokenConverter;
    }
  //endregion


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//        resources.tokenStore(jwtTokenStore);
        resources.tokenStore(redisTokenStore());

        //nvalid token does not contain resource id (oauth2-resource)
        resources.resourceId(DEMO_RESOURCE_ID);
//        resources.resourceId(null);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        /*
        -- 参考链接 https://blog.csdn.net/qq15035899256/article/details/129541483?utm_medium=distribute.pc_relevant.none-task-blog-2~default~baidujs_baidulandingword~default-1-129541483-blog-127025830.235^v38^pc_relevant_sort_base1&spm=1001.2101.3001.4242.2&utm_relevant_index=2

-- grant_type 来表明我们的授权方式  ①客户端模式（Client Credentials）
-- 获取授权码的回调地址 http://localhost:9001/loginSuccess
-- autoapprove  true 自动授权，不会弹出手动授权对话网页
-- "authorization_code","password","refresh_token"   ROLE_ADMIN  authorization_code,password,refresh_token   localhost

-- authorized_grant_types 值
-- authorization_code 授权码模式
-- password  密码模式
-- client_credentials 客户端模式
-- implicit
-- refresh_token 刷新token:token过期用refresh_token获取token，获取方式指定
 --              grant_type 指定为refresh_token,client_id、client_secret、
--               refresh_token

-- 认证码使用一次就作废 重新获取http://localhost:9001/oauth/authorize?client_id=client_id1&response_type=code


-- 权限作用域配置
--  字符服务器配置 scope access("#oauth2.hasScope('book')");
 -- 认证端配置  .scopes("book", "user", "borrow") 多个应该用逗号分割

 -- 过期时间单位秒，token 过期报错，只要不过期，token和refresh_token就可用
 --  "error": "invalid_token", "error_description": "Access token expired:


TRUNCATE TABLE  oauth_client_details;
INSERT INTO oauth_client_details VALUES('client_id1','project_api', '$2a$10$Z9UdI243Oan3o9GNwXve4.xdFme64BeRnGC92TLVEKHyi7Lg70LxO', 'all', 'authorization_code,password,refresh_token,client_credentials,implicit', 'http://localhost:9001/loginSuccess', 'ROLE_ADMIN', 30, 864000, NULL, 'true');
         */



        /*
         前段收到401的异常之后重定向到授权登录接口
         */
//        //白名单："/oauth/authorize",,"/user/login","/authentication/form"
//        http.
//                authorizeRequests().antMatchers("/login","/user/permitAccess").permitAll()
//                // 其余所有请求全部需要鉴权认证
//                .anyRequest().authenticated()
//                // 关闭跨域保护;
//                .and().csrf().disable();

        http
                .authorizeRequests().antMatchers("/login","/user/permitAccess").permitAll()
                .anyRequest().access("#oauth2.hasScope('all')") //添加自定义规则
                .and().csrf().disable();
        //Token必须要有我们自定义scope授权才可以访问此资源

    }
}
