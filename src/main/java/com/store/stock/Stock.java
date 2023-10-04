package com.store.stock;

import com.store.product.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Stock")
@Table(name = "stock")
public class Stock {
    @Id
    @SequenceGenerator(
            name = "stock_sequence",
            sequenceName = "stock_sequence"
    )
    @GeneratedValue(
            generator = "stock_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();

    private Integer quantity;

    public Stock() {
    }

    public Stock(List<Product> products, Integer quantity) {
        this.products = products;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void addProduct(Product product){
        if (!products.contains(product)){
            products.add(product);
        }
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", products=" + products +
                ", quantity=" + quantity +
                '}';
    }

}


