package com.store.stockMovement;

import com.store.stock.StockDTO;

import java.util.List;

public class StockMovementDTO {

    private Long id;
    private List<StockDTO> stockDTOs;

    public StockMovementDTO() {
    }

    public StockMovementDTO(List<StockDTO> stockDTOs) {
        this.stockDTOs = stockDTOs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<StockDTO> getStockDTOs() {
        return stockDTOs;
    }

    public void setStockDTOs(List<StockDTO> stockDTOs) {
        this.stockDTOs = stockDTOs;
    }

    @Override
    public String toString() {
        return "StockMovementDTO{" +
                "id=" + id +
                ", stockDTOs=" + stockDTOs +
                '}';
    }
}
