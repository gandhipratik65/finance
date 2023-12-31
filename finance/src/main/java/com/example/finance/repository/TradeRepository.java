package com.example.finance.repository;

import com.example.finance.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

// TradeRepository.java
public interface TradeRepository extends R2dbcRepository<Trade, Long> {
}
