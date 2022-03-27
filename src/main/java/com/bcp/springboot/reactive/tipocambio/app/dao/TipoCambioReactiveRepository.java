package com.bcp.springboot.reactive.tipocambio.app.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bcp.springboot.reactive.tipocambio.app.entity.*;

import reactor.core.publisher.Mono;

import org.springframework.data.r2dbc.repository.Query;

@Repository
public interface TipoCambioReactiveRepository extends ReactiveCrudRepository<TipoCambio, Long> {
	@Query(value = "SELECT * FROM tipocambio  " +
            "WHERE " +
            " id = :id " +
            " ")
    Mono<TipoCambio> customFindById(Long id);
	
	@Query(value = "SELECT * FROM tipocambio  " +
            "WHERE " +
            " monedaOrigen = :monedaOrigen " +
            " and monedaDestino = :monedaDestino ")
    Mono<TipoCambio> customFindByMonedaOrigenMonedaDestino(String monedaOrigen,String monedaDestino);
}