package com.bcp.springboot.reactive.tipocambio.app.service;

import com.bcp.springboot.reactive.tipocambio.app.entity.TipoCambio;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TipoCambioReactiveService {
	Mono<TipoCambio> customSave(TipoCambio tipoCambio);
	Mono<TipoCambio> customUpdate(TipoCambio tipoCambio);
	Mono<TipoCambio> customGetById(Long id);
	Flux<TipoCambio> findAll();
}
