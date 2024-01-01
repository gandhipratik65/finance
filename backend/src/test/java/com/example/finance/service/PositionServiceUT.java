package com.example.finance.service;

import com.example.finance.domain.Trade;
import com.example.finance.repository.TradeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PositionServiceTestUT {

    @Mock
    private TradeRepository tradeRepository;

    @InjectMocks
    private PositionService positionService;


    @Test
    void testGetPositionById() {
        Long id = 1L;
        // Mock the behavior of the repository
        when(tradeRepository.findById(id)).thenReturn(Mono.just(new Trade(id, "UPL", 100, 100.0)));

        // Call the service method
        Mono<Trade> result = positionService.getPositionById(id);

        // Verify the result
        StepVerifier.create(result)
                .expectNextMatches(trade -> trade.getId().equals(id))
                .verifyComplete();

        // Verify that the repository method was called
        verify(tradeRepository, times(1)).findById(id);
        verifyNoMoreInteractions(tradeRepository);
    }
}
