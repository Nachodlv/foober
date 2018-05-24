package model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "deliveryGuy")
public class DeliveryGuy extends UserAccount {
    @Expose
    @Column(name = "name")
    private String name;
    @Expose
    @Column(name = "phone")
    private long phone;
    @Column(name = "meansOfTransport")
    private int meansOfTransport;
    @OneToMany(mappedBy = "deliveryGuy", fetch = FetchType.EAGER)
    private Set<Order> orders;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private StateDG state;
    @Expose
    @Column(name = "ratingQuantity")
    private int ratingQuantity;
    @Expose
    @Column(name = "rating")
    private double rating;

    public DeliveryGuy(String email, String name, String password, long phone, int meansOfTransport) {
        super(email, password, "DG");
        this.name = name;
        this.phone = phone;
        this.meansOfTransport = meansOfTransport;
        orders = new HashSet<>();
        state = StateDG.OFFLINE;
        this.rating = 0;
        this.ratingQuantity = 0;
    }

    public DeliveryGuy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getMeansOfTransport() {
        return meansOfTransport;
    }

    public void setMeansOfTransport(int meansOfTransport) {
        this.meansOfTransport = meansOfTransport;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public StateDG getState() {
        return state;
    }

    public void setState(StateDG state) {
        this.state = state;
    }

    public double getRating() {
        return rating;
    }

    public void addRating(double rating){
        this.rating += rating;
        ratingQuantity++;
    }

    public int getRatingQuantity() {
        return ratingQuantity;
    }

    public void setRatingQuantity(int ratingQuantity) {
        this.ratingQuantity = ratingQuantity;
    }
}

