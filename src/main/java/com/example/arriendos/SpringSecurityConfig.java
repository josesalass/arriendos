package com.example.arriendos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailServiceImpl userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/assets/**")
                .antMatchers("/error");
                
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
        .antMatchers("/",
            "/css/**",
            "/js/**",
            "/images/**",
            "/residencia/list",
            "/usuario/nuevo",
            "/usuario/save",
            "/residencia/sortU",
            "/residencia/redirectionU/**",
            "/piezas/listResU/**",
            "/piezas/list").permitAll()
        .anyRequest().authenticated()
        .and()
        .formLogin()
            .defaultSuccessUrl("/residencia")
            .permitAll()
        .and()
        .logout().permitAll()
        .and()
        .exceptionHandling()
        .accessDeniedPage("/access-denied");
    }

    
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception 
    {
        build.userDetailsService(userDetailsService)
        .passwordEncoder(this.passwordEncoder());
    }
}
