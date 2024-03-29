package com.fancky.authorization.config;

import com.fancky.authorization.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.endpoint.TokenKeyEndpoint;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

@Configuration
//@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;
//    @Autowired
//    private AuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    @Autowired
//    @Qualifier("jwtTokenStore")
    private TokenStore jwtTokenStore;

//    @Autowired
    //    @Qualifier("jwtTokenStore")
//    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired
    private JwtTokenEnhancer jwtTokenEnhancer;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory ;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    public TokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory) ;
    }

    public TokenStore JdbcTokenStore(){
        return new JdbcTokenStore(dataSource) ;
    }

    /**
     * 使用密码模式需要配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsServiceImp)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .tokenStore(JdbcTokenStore())//存储在数据库
//                .tokenStore(jwtTokenStore) //配置令牌存储策略
                .tokenStore(redisTokenStore())//存储在redis


        ;



//        // JWT密码模式
//        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//        List<TokenEnhancer> delegates = new ArrayList<>();
//        delegates.add(jwtAccessTokenConverter);
//        enhancerChain.setTokenEnhancers(delegates);
//        endpoints.authenticationManager(authenticationManager)// 密码模式需要
//               // .authorizationCodeServices(authorizationCodeServices) // 授权模式需要
//                .userDetailsService(userDetailsServiceImp)
//                .tokenStore(jwtTokenStore) //配置令牌存储策略
//                .accessTokenConverter(jwtAccessTokenConverter)
////                .reuseRefreshTokens(true)//设置为false时，每次通过refresh_token获得access_token时， 也会刷新refresh_token
//                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//                .tokenEnhancer(enhancerChain);


//        /**
//         * jwt 增强模式
//         */
//        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
//        List<TokenEnhancer> enhancerList = new ArrayList<>();
//        enhancerList.add(jwtTokenEnhancer);
//        enhancerList.add(jwtAccessTokenConverter);
//        enhancerChain.setTokenEnhancers(enhancerList);
//        endpoints.tokenStore(jwtTokenStore)
//                .userDetailsService(userDetailsServiceImp)
//                /**
//                 * 支持 password 模式
//                 */
//                .authenticationManager(authenticationManager)
//                .tokenEnhancer(enhancerChain)
//                .accessTokenConverter(jwtAccessTokenConverter);


    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        /*
         java中命名不能以-连接，转换成_。
        client_id： client_id：
        client_secret：client_secret

         硬编码
         */
//        clients.inMemory()
//                .withClient("client_id1")//客户端ID和client_secret
//                .secret(passwordEncoder.encode("client_secret"))//设置client_secret这里密码需要进行加密
//                .accessTokenValiditySeconds(3600)
//                .refreshTokenValiditySeconds(864000) //设置刷新令牌失效时间864000
//                .redirectUris("http://localhost:9002/login") //单点登录时配置，访问客户端需要授权的接口，会跳转到该路径
//                .autoApprove(true) //自动授权配置
//                .resourceIds("project_api")
//                .scopes("all")
//                .authorizedGrantTypes("authorization_code","password","refresh_token")
//                .and()//在一个Memory中添加多个客户端
//                .withClient("client_id2")
//                .secret(passwordEncoder.encode("client_secret2"))//这里密码需要进行加密
//                .accessTokenValiditySeconds(3600)
//                .refreshTokenValiditySeconds(864000) //设置刷新令牌失效时间864000
//                .redirectUris("http://localhost:9002/login") //单点登录时配置，访问客户端需要授权的接口，会跳转到该路径
//                .autoApprove(true) //自动授权配置
//                .resourceIds("project_api")
//                .scopes("all")
//                .authorizedGrantTypes("authorization_code","password","refresh_token");


        //注意：客户端client_secret在数据库中是已密文的形式存在，要设置passwordEncoder。
       //$2a$10$o3ZPuHqWBXjPV7mLf25nxutni5/4Z2A9yNQg6LYtIGBMS3K4c31Rq
        //String encoderSecret = passwordEncoder.encode("client_secret");
        //把客户端信息配置在数据库中
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
        //DefaultOAuth2RequestFactory
        // 从数据库中读取客户端配置
        clients.withClientDetails(jdbcClientDetailsService());

        int m = 0;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();////表单认证（申请令牌）
        //客户端需要获取token key 和校验token key 信息校验是否登录，此处设置获取权限
        security.tokenKeyAccess("isAuthenticated()"); //获取token_key 接口获取公钥 获取密钥需要身份认证，使用单点登录时必须配置
        security.checkTokenAccess("isAuthenticated()");//获取token

//        security.checkTokenAccess("permitAll()");
    }

    //    @Bean
//    public TokenKeyEndpoint tokenKeyEndpoint() {
//        return new TokenKeyEndpoint(jwtAccessTokenConverter);
//    }
    @Bean
    public ClientDetailsService jdbcClientDetailsService() {
        // 基于JDBC实现，需要实现在数据库配置客户端信息以及密码加密方式
        JdbcClientDetailsService detailsService = new JdbcClientDetailsService(dataSource);
        detailsService.setPasswordEncoder(passwordEncoder);
        return detailsService;
    }

}
