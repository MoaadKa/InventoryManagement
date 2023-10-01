package com.store.provider;

import com.store.product.Product;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Provider")
@Table(name = "provider")
public class Provider {

    @Id
    @SequenceGenerator(
            name = "provider_sequence",
            sequenceName = "provider_sequence"
    )
    @GeneratedValue(
            generator = "provider_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String name;

    public Provider() {
    }

    public Provider(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Provider{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
