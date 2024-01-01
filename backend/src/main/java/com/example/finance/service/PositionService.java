package com.example.finance.service;

// PositionService.java
import com.example.finance.domain.Trade;
import com.example.finance.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@Service
public class PositionService {
    private final TradeRepository positionRepository;

    @Autowired
    public PositionService(TradeRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Flux<Trade> getAllPositions() {
       return Flux.fromIterable( Arrays.asList(new Trade(1l,"UPL",100,100.0),new Trade(3l,"UPL",100,100.0)));
       // return positionRepository.findAll();
    }

    public Mono<Trade> getPositionById(Long id) {
        return positionRepository.findById(id);
    }
}


