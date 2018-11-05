package com.makelife.server.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.authentication.CachingUserDetailsService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.JdkSerializationStrategy;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author sunyz
 * @desc
 * @create 2018-10-09 下午3:37
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    static final String CLIEN_ID = "devglan-client";
    static final String CLIENT_SECRET = "devglan-secret";
    static final String GRANT_TYPE_PASSWORD = "password";//资源所有者（即用户）密码类型
    static final String AUTHORIZATION_CODE = "authorization_code";//授权码类型
    static final String IMPLICIT = "implicit";//隐式授权类型
    static final String CLIENT_CREDENTIALS = "client_credentials";//客户端凭据（客户端ID以及Key）
    static final String REFRESH_TOKEN = "refresh_token";//通过以上授权获得的刷新令牌来获取新的令牌// 类型
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1*60*60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6*60*60;

    @Autowired
    private TokenStore tokenStore; //存内存

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private CachingUserDetailsService userDetailsService;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {

        configurer
                .inMemory()
                .withClient(CLIEN_ID)
                .resourceIds("front-server")
                .authorities("client")
                .secret(CLIENT_SECRET)
                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT,CLIENT_CREDENTIALS )
                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                .refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS);

//                .and().withClient(CLIEN_ID)
//                .resourceIds("front-server")
//                .authorizedGrantTypes("password", "refresh_token")
//                .scopes("select")
//                .authorities("client")
//                .secret("123456");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
        //todo 这里额外指定了/oauth/token的password模式要使用的userDetailsService
//        endpoints.tokenStore(tokenStore())
                .authenticationManager(authenticationManager);
//        endpoints.userDetailsService(new InMemoryUserDetailsManager());
//        endpoints.userDetailsService(new CachingUserDetailsService());
//        endpoints.userDetailsService(userDetailsService);
//        endpoints.userDetailsService(new MySecurityConfig().userDetailsServiceBean());
        endpoints.allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);// add get method
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //enable client to get the authenticated when using the /oauth/token to get a access token
        // there is a 401 authentication is required if it doesn't allow form authentication for clients when access /oauth/token
        oauthServer.allowFormAuthenticationForClients();
    }

    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore redis = new RedisTokenStore(connectionFactory);
        return redis;
    }

}
