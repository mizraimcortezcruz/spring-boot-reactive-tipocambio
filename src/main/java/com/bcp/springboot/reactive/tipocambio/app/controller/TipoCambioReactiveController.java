package com.bcp.springboot.reactive.tipocambio.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.bcp.springboot.reactive.tipocambio.app.dao.TipoCambioReactiveRepository;
import com.bcp.springboot.reactive.tipocambio.app.entity.TipoCambio;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@RestController
@RequiredArgsConstructor
@RequestMapping("/tipocambio")
public class TipoCambioReactiveController {
	@Autowired
	private TipoCambioReactiveRepository customerRepository;
	@PostMapping("/guardar")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TipoCambio> createCustomer(@RequestBody TipoCambio customer) {
        return customerRepository.save(customer);
    }
}
