package com.fancky.authorization.config;

import com.fancky.authorization.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.security.oauth2.provider.endpoint.TokenKeyEndpoint;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    @Qualifier("dataSource")
    private DataSource dataSource;

    /**
     * 使用密码模式需要配置
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        List<TokenEnhancer> delegates = new ArrayList<>();
        delegates.add(jwtAccessTokenConverter);
        enhancerChain.setTokenEnhancers(delegates);
        endpoints.authenticationManager(authenticationManager)
                .userDetailsService(userDetailsServiceImp)
                .tokenStore(jwtTokenStore) //配置令牌存储策略
                .accessTokenConverter(jwtAccessTokenConverter)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .tokenEnhancer(enhancerChain);


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
        clients.inMemory()
                .withClient("client_id")//客户端ID和Secret
                .secret(passwordEncoder.encode("client_secret"))//这里密码需要进行加密
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(864000) //设置刷新令牌失效时间864000
                .redirectUris("http://localhost:9002/login") //单点登录时配置，访问客户端需要授权的接口，会跳转到该路径
                .autoApprove(true) //自动授权配置
                .scopes("all")
                .authorizedGrantTypes("authorization_code","password","refresh_token")
                .and()//在一个Memory中添加多个客户端
                .withClient("client_id2")
                .secret(passwordEncoder.encode("client_secret2"))//这里密码需要进行加密
                .scopes("all")
                .authorizedGrantTypes("authorization_code","password","refresh_token");


//       //$2a$10$o3ZPuHqWBXjPV7mLf25nxutni5/4Z2A9yNQg6LYtIGBMS3K4c31Rq
//        String encoderSecret = passwordEncoder.encode("test_key");
//        // 把客户端信息配置在数据库中
//        clients.jdbc(dataSource)
//         .passwordEncoder(passwordEncoder);//数据库中secret不采用铭文
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.allowFormAuthenticationForClients();////表单认证（申请令牌）
        security.tokenKeyAccess("isAuthenticated()"); //获取token_key 接口获取公钥 获取密钥需要身份认证，使用单点登录时必须配置
        security.checkTokenAccess("isAuthenticated()");//获取token


//        security.checkTokenAccess("permitAll()");
    }

//    @Bean
//    public TokenKeyEndpoint tokenKeyEndpoint() {
//        return new TokenKeyEndpoint(jwtAccessTokenConverter);
//    }

}
