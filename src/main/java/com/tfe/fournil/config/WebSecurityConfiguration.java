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


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private String[] FREE_URLS =  { "/", "/home", "/register", "/addUser"};
    private String[] USER_URLS =  {"/myPersonalData", "/myPersonalData/*"};
    private String[] EMPLOYE_URLS = {"/feedbackList"};
    private String[] ADMIN_URLS =  {"/managementCategoryProduct"};

    @Autowired
    private UserDetailsService userDetailsService;

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
    }
}
