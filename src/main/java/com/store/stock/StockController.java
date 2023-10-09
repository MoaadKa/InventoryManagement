package com.store.stock;

import com.store.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @GetMapping(path = "stocks")
    public List<Stock> getStocks(){
        return stockService.getStocks();
    }

    @PostMapping(path = "create-stock")
    public void addStock(@RequestBody Stock stock){
        System.out.println("inserting stock");
        System.out.println(stock);
        stockService.addStock(stock);

        System.out.println("after stock was insrted");
    }

    @DeleteMapping(path = "{stockId}")
    public void deleteStock(@PathVariable(value = "stockId") Long stockId){
        stockService.deleteStock(stockId);
    }

    @PutMapping(path = "{stockId}")
    public void updateStock(
            @PathVariable Long clientId,
            @RequestParam(required = false) Integer quantity
    ){
        stockService.updateStock(clientId, quantity);
    }
}
