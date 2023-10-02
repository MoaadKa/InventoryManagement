package com.store.client;


import jakarta.persistence.*;

@Entity(name = "Client")
@Table(name = "client")
public class Client {
    @Id
    @SequenceGenerator(
            name = "client_sequence",
            sequenceName = "client_sequence"
    )
    @GeneratedValue(
            generator = "client_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;

    private String name;

    public Client() {
    }

    public Client(String name) {
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
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
