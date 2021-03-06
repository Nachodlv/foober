package model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "food_order")
public class Order {
    @Expose
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Expose
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StateOrder stateOrder;
    @Expose
    @Column(name = "elaborationTime")
    private int elaborationTime;
    @Expose
    @Column(name = "issuedTime")
    private long issuedTime;
    @Expose
    @ManyToOne
    @JoinColumn(name = "FO_email", nullable = false)
    private FranchiseOwner franchiseOwner;
    @Expose
    @ManyToOne
    @JoinColumn(name = "DG_email")
    private DeliveryGuy deliveryGuy;
    @Expose
    @ManyToOne
    @JoinColumn(name = "client_email", nullable = false)
    private Client client;
    @Expose
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderedProducts> orderedProducts = new HashSet<>();
    @Expose
    @Column(name = "ratingDG")
    private double ratingDG;
    @Expose
    @Column(name = "dgFirstLocation")
    private String dgFirstLocation;

    public Order() {
    }

    public Order(int elaborationTime, FranchiseOwner franchiseOwner, DeliveryGuy deliveryGuy, Client client) {
        this.stateOrder = StateOrder.WAITING;
        this.elaborationTime = elaborationTime;
        this.franchiseOwner = franchiseOwner;
        this.deliveryGuy = deliveryGuy;
        this.client = client;
        this.issuedTime = System.currentTimeMillis();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StateOrder getStateOrder() {
        return stateOrder;
    }

    public void setStateOrder(StateOrder stateOrder) {
        this.stateOrder = stateOrder;
    }

    public int getElaborationTime() {
        return elaborationTime;
    }

    public void setElaborationTime(int elaborationTime) {
        this.elaborationTime = elaborationTime;
    }

    public long getIssuedTime() {
        return issuedTime;
    }

    public void setIssuedTime(long issuedTime) {
        this.issuedTime = issuedTime;
    }

    public FranchiseOwner getFranchiseOwner() {
        return franchiseOwner;
    }

    public void setFranchiseOwner(FranchiseOwner franchiseOwner) {
        this.franchiseOwner = franchiseOwner;
    }

    public DeliveryGuy getDeliveryGuy() {
        return deliveryGuy;
    }

    public void setDeliveryGuy(DeliveryGuy deliveryGuy) {
        this.deliveryGuy = deliveryGuy;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<OrderedProducts> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Set<OrderedProducts> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (OrderedProducts product : orderedProducts) {
            totalCost += product.getProduct().getPrice() * product.getQuantity();
        }
        return totalCost;
    }

    public double getRatingDG() {
        return ratingDG;
    }

    public void setRatingDG(double ratingDG) {
        this.ratingDG = ratingDG;
    }

    public int getMinutesSinceIssued() {
        final long currentTime = System.currentTimeMillis();
        final long secondsFromIssued = TimeUnit.MILLISECONDS.toSeconds(currentTime - issuedTime);
        return (int) secondsFromIssued / 60;
    }

    public String getDgFirstLocation() {
        return dgFirstLocation;
    }

    public void setDgFirstLocation(String dgFirstLocation) {
        this.dgFirstLocation = dgFirstLocation;
    }
}