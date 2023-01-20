package com.example.productlist.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final UserDetailsService userDetailsService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
            (requests) ->
                requests
                    .antMatchers("/")
                    .permitAll()
                    .antMatchers("/product/*", "/category/*")
                    .hasRole("ADMIN"))
        .formLogin();

    return http.build();
  }

  @Bean
  public PasswordEncoder getPasswordEncoder(){
    return NoOpPasswordEncoder.getInstance();
  }
}

//  @Override
//  protected void configure(HttpSecurity http) throws Exception {
//    http.authorizeHttpRequests()
//            .antMatchers("/product", "/category").hasRole("ADMIN")
//            .antMatchers("/store").hasAnyRole("USER", "ADMIN")
//            //.antMatchers("/").permitAll()
//            .and()
//            .formLogin(); (form) -> form.loginPage("/login").permitAll())
//        .logout(LogoutConfigurer::permitAll);
// .permitAll()
//        .anyRequest().authenticated()
//  }
