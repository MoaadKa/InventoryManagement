package com.store.stockMovement;

import com.store.provider.Provider;
import jakarta.persistence.*;

@Entity(name = "StockMovement")
@Table(name = "stock_movement")
public class StockMovement {

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
    private Integer movementQuantity;

    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "provider_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "provider_id_fk")
    )
    private Provider provider;

    public StockMovement() {
    }

    public StockMovement(Integer movementQuantity, Provider provider) {
        this.movementQuantity = movementQuantity;
        this.provider = provider;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMovementQuantity() {
        return movementQuantity;
    }

    public void setMovementQuantity(Integer movementQuantity) {
        this.movementQuantity = movementQuantity;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "StockMovement{" +
                "id=" + id +
                ", movementQuantity=" + movementQuantity +
                ", provider=" + provider +
                '}';
    }
}
