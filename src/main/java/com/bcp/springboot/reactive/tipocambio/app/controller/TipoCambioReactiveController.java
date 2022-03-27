package com.bcp.springboot.reactive.tipocambio.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.springboot.reactive.tipocambio.app.entity.TipoCambio;
import com.bcp.springboot.reactive.tipocambio.app.service.TipoCambioReactiveServiceImpl;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
@RestController
@RequiredArgsConstructor
@RequestMapping("/tipocambio")
public class TipoCambioReactiveController {
	@Autowired
	private TipoCambioReactiveServiceImpl tipoCambioReactiveServiceImpl;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/guardar")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TipoCambio> guardarTipoCambio(@RequestBody TipoCambio tipoCambio) {
		logger.info("TipoCambioReactiveController guardarTipoCambio");
        return tipoCambioReactiveServiceImpl.customSave(tipoCambio);
    }
	
	@PostMapping("/actualizar")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TipoCambio> actualizarTipoCambio(@RequestBody TipoCambio tipoCambio) {
		logger.info("TipoCambioReactiveController actualizarTipoCambio");
        return tipoCambioReactiveServiceImpl.customUpdate(tipoCambio);
    }
}
