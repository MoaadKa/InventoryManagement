package com.store.stockMovement;

import com.store.stock.Stock;
import com.store.stock.StockDTO;
import com.store.stock.StockRepository;
import com.store.stock.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/movement")
public class StockMovementController {

    private final StockService stockService;
    private final StockMovementRepository stockMovementRepository;

    private final StockRepository stockRepository;

    @Autowired
    public StockMovementController(StockService stockService, StockMovementRepository stockMovementRepository, StockRepository stockRepository) {
        this.stockService = stockService;
        this.stockMovementRepository = stockMovementRepository;
        this.stockRepository = stockRepository;
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

   /* @PostMapping(path = "provider-action-v2")
    public void providerAction2(@RequestBody StockMovement stockMovement){
        stockService.providerQuantity(
                stockMovement.getStock().getId(),
                stockMovement.getMovementQuantity()
                );
        stockMovementRepository.save(stockMovement);
    }
*/

    @PostMapping(path = "provider-delivery")
    public void providerDelivery(@RequestBody StockMovementDTO stockMovementDTO){
        StockMovement stockMovement = new StockMovement();
        stockMovement.setId(stockMovementDTO.getId());
        List<StockDTO> stockDTOs = stockMovementDTO.getStockDTOs();
        for (StockDTO stockDTO : stockDTOs){
            stockService.providerQuantity(stockDTO.getId(),stockDTO.getQuantity());
        }
        stockMovementRepository.save(stockMovement);
    }

    @PostMapping(path = "client-purchase")
    public void clientPurchase(@RequestBody StockMovementDTO stockMovementDTO){
        StockMovement stockMovement = new StockMovement();
        stockMovement.setId(stockMovementDTO.getId());
        List<StockDTO> stockDTOs = stockMovementDTO.getStockDTOs();
        for (StockDTO stockDTO : stockDTOs){
            stockService.clientQuantity(stockDTO.getId(),stockDTO.getQuantity());
        }
        stockMovementRepository.save(stockMovement);
    }

    private StockMovement convertToEntity(StockMovementDTO stockMovementDTO){
        StockMovement stockMovement = new StockMovement();
        stockMovement.setId(stockMovementDTO.getId());
        for(StockDTO stockDTO : stockMovementDTO.getStockDTOs()){
            Stock stock = stockRepository.getReferenceById(stockDTO.getId());
            stock.setQuantity(stock.getQuantity());
        }
        return stockMovement;
    }

}
