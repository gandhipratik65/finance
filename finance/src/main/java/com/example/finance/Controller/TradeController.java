package com.example.finance.Controller;

import com.example.finance.domain.Trade;
import com.example.finance.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

// TradeController.java
@RestController
@RequestMapping("/api/trades")
public class TradeController {
    private final TradeService tradeService;

    @Autowired
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @PostMapping("/place")
    public Mono<ResponseEntity<Trade>> placeTrade(@RequestBody Trade trade) {
        return tradeService.placeTrade(trade) .map(position -> new ResponseEntity<>(position, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

