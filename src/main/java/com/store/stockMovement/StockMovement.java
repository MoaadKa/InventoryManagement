package com.store.stockMovement;

import com.store.client.Client;
import com.store.provider.Provider;
import com.store.stock.Stock;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "StockMovement")
@Table(name = "stock_movement")
public class StockMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(
            cascade = CascadeType.MERGE
    )
/*    @JoinColumn(
            name = "stock_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "stock_id_fk")
    )*/
    private List<Stock> stocks = new ArrayList<>();

    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "provider_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "provider_id_fk")
    )
    private Provider provider;

    @ManyToOne(
            cascade = CascadeType.MERGE
    )
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "client_id_fk")
    )
    private Client client;

    public StockMovement() {
    }

    public StockMovement(List<Stock> stocks, Provider provider, Client client) {
        this.stocks = stocks;
        this.provider = provider;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    @Override
    public String toString() {
        return "StockMovement{" +
                "id=" + id +
                ", stocks=" + stocks +
                ", provider=" + provider +
                ", client=" + client +
                '}';
    }
}
