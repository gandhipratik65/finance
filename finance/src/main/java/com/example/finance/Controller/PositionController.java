package com.example.finance.Controller;

// PositionController.java
import com.example.finance.domain.Trade;
import com.example.finance.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/positions")
public class PositionController {
    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public Flux<Trade> getAllPositions() {
        return positionService.getAllPositions();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Trade>> getPositionById(@PathVariable Long id) {
        return positionService.getPositionById(id)
                .map(position -> new ResponseEntity<>(position, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

