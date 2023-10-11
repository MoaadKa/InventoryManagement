package com.store.stock;

public class StockDTO {

    Long id;
    Integer quantity;

    public StockDTO() {
    }

    public StockDTO(Long id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StockDTO{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }
}
