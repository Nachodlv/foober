package model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @Expose
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Expose
    @Column(name = "name")
    private String name;
    @Expose
    @Column(name = "price")
    private double price;
    @Column(name = "active")
    private boolean active = true;
    @ManyToOne
    @JoinColumn(name = "FO_email", nullable = false)
    private FranchiseOwner franchiseOwner;
    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    private Set<OrderedProducts> orders = new HashSet<>();


    public Product() {
    }

    public Product(String name, double price, FranchiseOwner franchiseOwner) {
        this.name = name;
        this.price = price;
        this.franchiseOwner = franchiseOwner;
    }

    public FranchiseOwner getFranchiseOwner() {
        return franchiseOwner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setFranchiseOwner(FranchiseOwner franchiseOwner) {
        this.franchiseOwner = franchiseOwner;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<OrderedProducts> getOrders() {
        return orders;
    }

    public void setOrders(Set<OrderedProducts> orders) {
        this.orders = orders;
    }
}