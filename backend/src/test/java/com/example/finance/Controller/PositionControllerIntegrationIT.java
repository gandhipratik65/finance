package com.example.finance.Controller;

import com.example.finance.domain.Trade;
import com.example.finance.service.PositionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class PositionControllerIntegrationIT {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PositionService positionService;

    @Test
    public void testGetAllPositions() {
        // Mock the service response
        Trade trade1 = new Trade(1l,"UPL",100,100.0);
        Trade trade2 = new Trade(3l,"UPL",100,100.0);
        when(positionService.getAllPositions()).thenReturn(Flux.just(trade1, trade2));

        // Perform the GET request
        webTestClient.get()
                .uri("/api/positions")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Trade.class)
                .hasSize(2);
    }

    @Test
    public void testGetPositionById() {
        // Mock the service response
        Trade trade = new Trade(3l,"UPL",100,100.0);
        when(positionService.getPositionById(1L)).thenReturn(Mono.just(trade));

        // Perform the GET request
        webTestClient.get()
                .uri("/api/positions/1")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody(Trade.class)
                .isEqualTo(trade);
    }

    @Test
    public void testGetPositionById_NotFound() {
        // Mock the service response for a non-existent position
        when(positionService.getPositionById(999L)).thenReturn(Mono.empty());

        // Perform the GET request
        webTestClient.get()
                .uri("/api/positions/999")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }
}
