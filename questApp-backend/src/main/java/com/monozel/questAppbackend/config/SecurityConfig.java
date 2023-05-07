//package com.monozel.questAppbackend.config;
//
//import com.monozel.questAppbackend.security.JwtAuthenticationEntryPoint;
//import com.monozel.questAppbackend.security.JwtAuthenticationFilter;
//import com.monozel.questAppbackend.services.UserDetailsServiceImpl;
//import org.apache.catalina.filters.CorsFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    private UserDetailsServiceImpl userDetailsService;
//    private JwtAuthenticationEntryPoint handler;
//    private AuthenticationProvider authenticationProvider;
//
//    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint handler) {
//        this.handler = handler;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Bean
//    public JwtAuthenticationFilter jwtAuthenticationFilter() {
//        return new JwtAuthenticationFilter();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder () {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration)
//            throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public CorsFilter corsFilter () {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedHeader("*");
//        config.addAllowedOrigin("*");
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("HEAD");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("PUT");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("PATCH");
//        source.registerCorsConfiguration("/**",config);
//        return new CorsFilter();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
////        httpSecurity
////                .cors()
////                .and()
////                .csrf().disable()
////                .exceptionHandling().authenticationEntryPoint(handler).and()
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
////                .authorizeRequests()
////                .antMatchers(HttpMethod.GET, "/posts")
////                .permitAll()
////                .antMatchers(HttpMethod.GET, "/comments")
////                .permitAll()
////                .antMatchers("/auth/**")
////                .permitAll
////                .anyRequest().authenticated();
//        httpSecurity
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers(
//                        "/posts",
//                        "/comments",
//                        "/auth/**"
//                )
//                .permitAll()
//                .anyRequest()
//                .authenticated().and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                .logout()
//                .logoutUrl("/api/v1/auth/logout")
//                .addLogoutHandler()
//                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
//                ;
//        return httpSecurity.build();
//    }
//}
