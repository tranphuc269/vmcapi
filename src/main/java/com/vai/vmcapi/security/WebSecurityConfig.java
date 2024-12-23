package com.vai.vmcapi.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    private final CustomUserDetailsService userAuthService;

    private final JwtProvider tokenProvider;


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userAuthService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(tokenProvider, userAuthService);
    }

    private static final String[] WHITELIST_URLS = {
            "/swagger-ui/**",
            "/swagger-resources/*",
            "/v3/api-docs/**",
            "/users/auth",
            "/users/register",
            "/cars/{userId}/users",
            "/test/**",
            "/test",
            "/address/**",
            "/brands/query",
            "/brands",
            "/car-buying-articles/query",
            "/cars/query",
            "/cars/**",
            "colors",
            "/colors/**",
            "files/**",
            "/files",
            "/fuels",
            "/fuels/**",
            "/models",
            "/models/**",
            "/origins",
            "/origins/**",
            "styles",
            "/styles/**",
            "/users/register"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(WHITELIST_URLS)
                            .permitAll()
                            .requestMatchers(HttpMethod.OPTIONS)
                            .permitAll()
                            .requestMatchers(HttpMethod.POST, "/cars")
                            .authenticated()
                            .requestMatchers(HttpMethod.POST, "/colors")
                            .authenticated()
                            .requestMatchers(HttpMethod.POST, "/brands")
                            .authenticated()
                            .requestMatchers(HttpMethod.POST, "/models")
                            .authenticated()
                            .requestMatchers(HttpMethod.POST, "/origins")
                            .authenticated()
                            .requestMatchers(HttpMethod.POST, "/fuels")
                            .authenticated()
                            .requestMatchers(HttpMethod.POST, "/styles")
                            .authenticated()
                            .requestMatchers(HttpMethod.POST, "/styles")
                            .authenticated()
                            .anyRequest()
                            .authenticated();
                })
                .authenticationProvider(authenticationProvider())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
