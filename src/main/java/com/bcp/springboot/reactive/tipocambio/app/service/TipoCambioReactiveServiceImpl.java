package com.bcp.springboot.reactive.tipocambio.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bcp.springboot.reactive.tipocambio.app.dao.TipoCambioReactiveRepository;
import com.bcp.springboot.reactive.tipocambio.app.entity.TipoCambio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class TipoCambioReactiveServiceImpl implements TipoCambioReactiveService{
	@Autowired
	private TipoCambioReactiveRepository tipoCambioReactiveRepository;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public Mono<TipoCambio> customSave(TipoCambio tipoCambio) {
		logger.info("TipoCambioReactiveServiceImpl customSave");
		return  tipoCambioReactiveRepository.save(tipoCambio);
	}
	@Override
	public Mono<TipoCambio> customUpdate(TipoCambio tipoCambio) {
		logger.info("TipoCambioReactiveServiceImpl customUpdate");
		return tipoCambioReactiveRepository
			.findById(tipoCambio.getId())
			.map(c->tipoCambio)
			.flatMap(tipoCambioReactiveRepository::save);	
	}
	@Override
	public Mono<TipoCambio> customGetById(Long id) {
		logger.info("TipoCambioReactiveServiceImpl customGetById");
		return tipoCambioReactiveRepository.customFindById(id);
	}
	@Override
	public Flux<TipoCambio> findAll() {
		logger.info("TipoCambioReactiveServiceImpl findAll");
		return tipoCambioReactiveRepository.findAll();
	}
	@Override
	public Mono<TipoCambio> customGetByMonedaOrigenMonedaDestino(String monedaOrigen, String monedaDestino) {
		logger.info("TipoCambioReactiveServiceImpl customGetByMonedaOrigenMonedaDestino");
		return tipoCambioReactiveRepository.customFindByMonedaOrigenMonedaDestino(monedaOrigen, monedaDestino);
	}
	@Override
	public Mono<TipoCambio> retrieveExchangeValue(Double montoOrigen, String monedaOrigen, String monedaDestino) {
		logger.info("TipoCambioReactiveServiceImpl retrieveExchangeValue");
		Mono<TipoCambio> tm=this.customGetByMonedaOrigenMonedaDestino(monedaOrigen, monedaDestino)
		.map(tmdb->{
			Double montoTipoCambio=montoOrigen*tmdb.getMontoDestino();
			tmdb.setMontoDestino(montoTipoCambio);
			return tmdb;
		});
		return tm;
	}
	
}
