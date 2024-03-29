package com.example.compmanage.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // Create 2 users for demo
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}123456").roles("ADMIN");

    }
    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/companies/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/company/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/company/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PATCH, "/api/products/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/company/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        //ok for demo
//        User.UserBuilder users = User.withDefaultPasswordEncoder();
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(users.username("user").password("123456").roles("USER").build());
//        manager.createUser(users.username("admin").password("123456").roles("USER", "ADMIN").build());
//        return manager;
//    }

}
