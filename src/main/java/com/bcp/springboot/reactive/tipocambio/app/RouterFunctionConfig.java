package com.bcp.springboot.reactive.tipocambio.app;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.bcp.springboot.reactive.tipocambio.app.handler.TipoCambioHandler;
@Configuration
public class RouterFunctionConfig {
	@Bean
	public RouterFunction<ServerResponse> routes(TipoCambioHandler handler){
		return route(GET("/tipocambio/getAll").or(GET("/tipocambio/v1/getAll")), handler::listar)
				.andRoute(GET("/tipocambio/{id}"), handler::ver);
	}
}