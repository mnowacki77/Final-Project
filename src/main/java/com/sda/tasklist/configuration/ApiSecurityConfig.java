package com.sda.tasklist.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@Order(1)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public ApiSecurityConfig(DataSource dataSource, PasswordEncoder bCryptPasswordEncoder) {
        this.dataSource = dataSource;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/api/v1/todos")
                .authorizeRequests()
                .anyRequest().hasAnyAuthority("ROLE_USER")
                .and().httpBasic();

//        http.csrf().disable()
//                .antMatcher("/api/v1/todos/**")
//                .authorizeRequests()
//                .anyRequest().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("SELECT u.login, u.password,1 FROM USER_ENTITY u WHERE u.login=?")
                .authoritiesByUsernameQuery("SELECT u.login, r.name, 1 " +
                        "FROM user_entity u " +
                        "INNER JOIN user_entity_roles ur ON ur.user_entity_id = u.id " +
                        "INNER JOIN user_role_entity r ON r.id = ur.roles_id " +
                        "WHERE u.login=?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }
}