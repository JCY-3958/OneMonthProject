package com.codeqna.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf(csrf -> csrf.disable());

        http.formLogin((it) -> it
                .loginPage("/users/login")
                .defaultSuccessUrl("/Loginmain")
                .usernameParameter("email")
                .failureUrl("/users/login/error")
        );

        http.logout((it) -> it
                .logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
                .logoutSuccessUrl("/main")
        );

        http.authorizeHttpRequests((req)-> {req
                .requestMatchers(antMatcher("/favicon.ico")).permitAll()
                .requestMatchers(antMatcher("/main")).permitAll()
                .requestMatchers(antMatcher("/users/**")).permitAll()
                .requestMatchers(antMatcher("/img/**")).permitAll()
                .requestMatchers(antMatcher("/boardAPI/**")).permitAll()
                .requestMatchers(antMatcher("/fileAPI/**")).permitAll()
                .requestMatchers(antMatcher("/vendor/**")).permitAll()
                /*.requestMatchers(antMatcher("/viewboard/**")).permitAll()*/
                .requestMatchers(antMatcher("/admin/**")).hasRole("ADMIN")
                .requestMatchers(antMatcher("/Loginmain")).hasAnyRole("USER","ADMIN")

                .anyRequest().authenticated();
        });

//        http.exceptionHandling((e) -> e.authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
}
