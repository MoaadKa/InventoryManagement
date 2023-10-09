package com.store.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    @Autowired
    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<Stock> getStocks(){
        return stockRepository.findAll();
    }

    public void addStock(Stock stock){
        stockRepository.save(stock);
    }

    public void deleteStock(Long stockId){
        if (!stockRepository.existsById(stockId)){
            throw new IllegalStateException("stock with id: " + stockId + " doesn't exist");
        }
        stockRepository.deleteById(stockId);
    }

    public void updateStock(Long stockId, Integer quantity){
        Optional<Stock> optionalStock = stockRepository.findById(stockId);
        Stock stock = optionalStock.orElseThrow(
                () -> new IllegalStateException("Not Found")
        );
        stock.setQuantity(quantity);
    }

    public void updateQuantity(Long stockId, Integer movementQuantity) {

        // getting the stock by its id
        Stock stock = stockRepository.getReferenceById(stockId);
        // getting the existing quantity
        Integer oldQuantity = stock.getQuantity();
        Integer newQuantity = oldQuantity + movementQuantity;
        // adding the new quantity from the provider
        stock.setQuantity(newQuantity);
        stockRepository.save(stock);
    }
}
