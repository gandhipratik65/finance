package com.example.finance.service;

import com.example.finance.domain.Trade;
import com.example.finance.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TradeService {
    private final TradeRepository tradeRepository;

    @Autowired
    public TradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Mono<Trade> placeTrade(Trade trade) {
        // Add logic for placing a trade, e.g., interaction with a brokerage API
        // Save the trade to the database
        return tradeRepository.save(trade);
    }
}
