package com.store.stockMovement;

import com.store.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/movement")
public class StockMovementController {

    private final StockService stockService;

    @Autowired
    public StockMovementController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping(path = "provider-action")
    public void providerAction(
            @RequestParam(name = "provider_id") Long providerId,
            @RequestParam(name = "stock_id") Long stockId,
            @RequestParam(name = "movement_quantity") Integer movementQuantity
    ){
        stockService.providerQuantity(stockId, movementQuantity);
    }

    @PostMapping(path = "client-action")
    public void clientAction(
            @RequestParam(name = "client_id") Long clientId,
            @RequestParam(name = "stock_id") Long stockId,
            @RequestParam(name = "movement_quantity") Integer movementQuantity
    ){
        stockService.clientQuantity(stockId, movementQuantity);
    }


}
