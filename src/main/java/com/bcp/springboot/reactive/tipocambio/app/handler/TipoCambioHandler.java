package com.bcp.springboot.reactive.tipocambio.app.handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import com.bcp.springboot.reactive.tipocambio.app.service.TipoCambioReactiveServiceImpl;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.BodyInserters.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bcp.springboot.reactive.tipocambio.app.entity.TipoCambio;
@Component
public class TipoCambioHandler {
	@Autowired
	private TipoCambioReactiveServiceImpl tipoCambioReactiveServiceImpl;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public Mono<ServerResponse> listar(ServerRequest request){
		logger.info("TipoCambioHandler listar");
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(tipoCambioReactiveServiceImpl.findAll(), TipoCambio.class);
	}
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasAnyRole('USER','ADMIN')")
	public Mono<ServerResponse> ver(ServerRequest request){
		logger.info("TipoCambioHandler ver");
		String id = request.pathVariable("id");
		Long codigo = Long.parseLong(id);
		return tipoCambioReactiveServiceImpl.customGetById(codigo).flatMap( p -> ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(fromObject(p)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
	@SuppressWarnings("deprecation")
	@PreAuthorize("hasRole('USER')")
	public Mono<ServerResponse> retrieveExchangeValue(ServerRequest request){
		logger.info("TipoCambioHandler retrieveExchangeValue");
		String montoDestino = request.pathVariable("montoDestino");
		String monedaOrigen = request.pathVariable("monedaOrigen");
		String monedaDestino = request.pathVariable("monedaDestino");
		Double mtoDestino=Double.parseDouble(montoDestino);
		
		return tipoCambioReactiveServiceImpl.retrieveExchangeValue(mtoDestino,monedaOrigen,monedaDestino).flatMap( p -> ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.body(fromObject(p)))
				.switchIfEmpty(ServerResponse.notFound().build());
	}
}