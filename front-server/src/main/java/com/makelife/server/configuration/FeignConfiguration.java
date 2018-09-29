package com.makelife.server.configuration;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sunyz
 * @desc
 * @create 2018-09-29 下午2:54
 */
@Configuration
public class FeignConfiguration {

    @Value("${security.user.name}")
    private String userName;

    @Value("${security.user.password}")
    private String passWord;

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor(userName, passWord);
    }
}
