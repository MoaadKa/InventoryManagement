package com.store.stock;

import com.store.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

   /* @GetMapping(path = "stocks")
    public List<Stock> getStocks(){
        return stockService.getStocks();
    }*/

    @GetMapping(path = "stocks")
    public List<StockDTO> getStocks(){
        return stockService.getStocks().stream().
                map(stock -> convertToDTO(stock)).
                collect(Collectors.toList());
    }



    @PostMapping(path = "create-stock")
    public void addStock(@RequestBody StockDTO stockDTO){

        Stock stock = convertToEntity(stockDTO);
        stockService.addStock(stock);
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

    private Stock convertToEntity(StockDTO stockDTO){
        Stock stock = new Stock();
        stock.setId(stockDTO.getId());
        stock.setQuantity(stockDTO.getQuantity());
        stock.setProduct(stockDTO.getProduct());
        return stock;
    }

    private StockDTO convertToDTO(Stock stock){
        StockDTO stockDTO = new StockDTO();
        stockDTO.setId(stock.getId());
        stockDTO.setQuantity(stock.getQuantity());
        stockDTO.setProduct(stock.getProduct());
        return stockDTO;
    }
}
