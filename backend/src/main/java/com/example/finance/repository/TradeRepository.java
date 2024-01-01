package com.example.finance.repository;

import com.example.finance.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

// TradeRepository.java
public interface TradeRepository extends R2dbcRepository<Trade, Long> {

    @Query("SELECT * FROM positions")
    Flux<Trade> findAll();

    @Query("SELECT * FROM positions WHERE id = :id")
    Mono<Trade> findById(Long id);
}

