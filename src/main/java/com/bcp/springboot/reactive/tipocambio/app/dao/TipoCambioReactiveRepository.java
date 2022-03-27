package com.bcp.springboot.reactive.tipocambio.app.dao;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.bcp.springboot.reactive.tipocambio.app.entity.*;

@Repository
public interface TipoCambioReactiveRepository extends ReactiveCrudRepository<TipoCambio, Long> {

}
