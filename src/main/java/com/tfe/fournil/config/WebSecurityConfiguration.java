package com.tfe.fournil.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * The type Web security configuration.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private String[] FREE_URLS =  { "/", "/imageProduct/**","/home", "/register", "/addUser", "/passwordForgot", "/passwordForgot/checkIfEmailExist" };
    private String[] USER_URLS =  {"/myPersonalData", "/myPersonalData/*", "/orderRecap"};
    private String[] EMPLOYE_URLS = {"/feedbackList",  "/feedbackList/statutNonlu",
            "/feedbackList/delete","/category/*", "/orderManage", "/orderPreparation"};
    private String[] ADMIN_URLS =  {"/managementCategoryProduct", "/feedbackList/statutlu", "/feedbackList/statutlu","/shop"};
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * Authentication provider authentication provider.
     *
     * @return the authentication provider
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider
                = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());

        return  provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
                .authorizeRequests()

                .antMatchers(FREE_URLS)
                .permitAll()
                .antMatchers(USER_URLS)
                .hasAnyAuthority(Authority.USER.name(), Authority.EMPLOYEE.name(), Authority.ADMIN.name())
                .antMatchers(EMPLOYE_URLS)
                .hasAnyAuthority(Authority.EMPLOYEE.name(), Authority.ADMIN.name())
                .antMatchers(ADMIN_URLS)
                .hasAuthority(Authority.ADMIN.name())
                .and()
                .formLogin().permitAll()
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .httpBasic();
//        http.headers()
//                .xssProtection()
//                .and()
//                .contentSecurityPolicy("script-src 'self'");

    }
}

