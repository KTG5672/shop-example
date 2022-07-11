package com.side.shop.com.config;

import com.side.shop.com.security.SecurityTestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final SecurityTestFilter securityTestFilter;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable()
        .httpBasic().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests(
            (auth) -> auth
                .antMatchers("/", "/auth/**").permitAll()
                .anyRequest().authenticated()
        );
    http.addFilterAfter(securityTestFilter, CorsFilter.class);
    return http.build();
  }
}
