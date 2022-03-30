package com.bcp.springboot.reactive.tipocambio.app.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

/* similar al spring SpringSecurityConfig
 * En este componente puede configurar todas sus necesidades de seguridad, 
 * como el administrador de autenticación, el repositorio de contexto de seguridad, 
 * qué URL está permitida
 * */
@AllArgsConstructor
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
    private SecurityContextRepository securityContextRepository;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(ServerHttpSecurity http) {
    	logger.info("(2) WebSecurityConfig securitygWebFilterChain");
        return http
                .exceptionHandling()
                .authenticationEntryPoint((swe, e) -> 
                    Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
                ).accessDeniedHandler((swe, e) -> 
                    Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
                ).and()
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/login").permitAll()
                //.pathMatchers("/tipocambio/getAll").permitAll()
                //.pathMatchers("/tipocambio/getById/**").permitAll()
                .anyExchange().authenticated()
                .and().build();
//    	http
//        .authorizeExchange()
//        .pathMatchers("/login").permitAll()
//        .pathMatchers("/tipocambio/**").permitAll()
//             .anyExchange().authenticated()
//                  .and()
//             .httpBasic()
//             	.disable()
//             //.and()
//             .csrf().disable()
//             .formLogin()
//             	.disable();
//    	return http.build();
    }
}
