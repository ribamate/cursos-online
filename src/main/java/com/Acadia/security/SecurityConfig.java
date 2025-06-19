package com.Acadia.security;

import com.Acadia.service.UsuarioDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        private final UsuarioDetailsService usuarioDetailsService;
        private final CustomSuccessHandler customSuccessHandler;

        public SecurityConfig(UsuarioDetailsService usuarioDetailsService,
                        CustomSuccessHandler customSuccessHandler) {
                this.usuarioDetailsService = usuarioDetailsService;
                this.customSuccessHandler = customSuccessHandler;
        }

        public SecurityConfig(UsuarioDetailsService usuarioDetailsService) {
                this.usuarioDetailsService = usuarioDetailsService;
                this.customSuccessHandler = null;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/", "/login", "/usuarios/cadastro",
                                                                "/usuarios/cadastrar", "/css/**",
                                                                "/js/**", "/img/**")
                                                .permitAll()
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .loginProcessingUrl("/login")
                                                .successHandler(customSuccessHandler) //
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutSuccessUrl("/login?logout")
                                                .permitAll());

                return http.build();
        }

        @Bean
        public AuthenticationManager authManager(HttpSecurity http) throws Exception {
                return http.getSharedObject(AuthenticationManagerBuilder.class)
                                .userDetailsService(usuarioDetailsService)
                                .passwordEncoder(passwordEncoder())
                                .and()
                                .build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return NoOpPasswordEncoder.getInstance(); // NÃO recomendado em produção!
        }

}