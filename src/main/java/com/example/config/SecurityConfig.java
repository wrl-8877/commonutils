//package com.example.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.stereotype.Component;
//
//@Component
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    /**
//     * 添加授权账号
//     *
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // 设置用户账号信息和权限
//        auth.inMemoryAuthentication().withUser("mayikt").password("mayikt").authorities("/");
//    }
//
//    /**
//     * 配置HttpSecurity 拦截资源
//     *
//     * @param http
//     * @throws Exception
//     */
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/**").fullyAuthenticated().and().httpBasic();
//    }
//
//
//}
