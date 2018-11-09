package com.makelife.zuulserver.configuration;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author sunyz
 * @desc
 * @create 2018-10-11 上午10:44
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {

//    public static MySecurityConfig instance = new MySecurityConfig();
//
//    public static MySecurityConfig getInstance() {
//        return instance;
//    }

//    private MySecurityConfig(){}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/**").permitAll()
//                .antMatchers("/amchart/**",
//                        "/bootstrap/**",
//                        "/build/**",
//                        "/css/**",
//                        "/dist/**",
//                        "/documentation/**",
//                        "/fonts/**",
//                        "/js/**",
//                        "/pages/**",
//                        "/users/**",
//                        "/plugins/**"
//                ).permitAll() //默认不拦截静态资源的url pattern （2）
                .anyRequest().authenticated().and()
//                .formLogin().loginPage("/login")// 登录url请求路径 (3)
//                .defaultSuccessUrl("/httpapi").permitAll().and() // 登录成功跳转路径url(4)
                .logout().permitAll();

        http.logout().logoutSuccessUrl("/"); // 退出默认跳转页面 (5)

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("root")
                .password("root")
                .roles("USER")
                .and()
                .withUser("admin").password("admin")
                .roles("ADMIN", "USER")
                .and()
                .withUser("user").password("user")
                .roles("USER");

//        auth.userDetailsService(this.userDetailsServiceBean());
    }
}
