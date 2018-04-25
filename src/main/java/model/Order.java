package model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "food_order")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "status")
    private boolean status;
    @Column(name = "elaborationTime")
    private int elaborationTime;
    @Column(name = "issuedTime")
    private long issuedTime;
    @ManyToOne
    @JoinColumn(name = "FO_email", nullable = false)
    private FranchiseOwner franchiseOwner;
    @ManyToOne
    @JoinColumn(name = "DG_email")
    private DeliveryGuy deliveryGuy;
    @ManyToOne
    @JoinColumn(name = "client_email", nullable = false)
    private Client client;
    @OneToMany(mappedBy = "order")
    private Set<OrderedProducts> orderedProducts = new HashSet<>();

    public Order(boolean status, int elaborationTime, FranchiseOwner franchiseOwner, DeliveryGuy deliveryGuy, Client client, Set<OrderedProducts> products) {
        this.status = status;
        this.elaborationTime = elaborationTime;
        this.issuedTime = System.currentTimeMillis();
        this.franchiseOwner = franchiseOwner;
        this.deliveryGuy = deliveryGuy;
        this.client = client;
        this.orderedProducts = products;
    }

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getElaborationTime() {
        return elaborationTime;
    }

    public void setElaborationTime(int elaborationTime) {
        this.elaborationTime = elaborationTime;
    }

    public long getIssuedTime() {
        return issuedTime;
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

    public int getTotalCost(){
        int totalCost = 0;
        for (OrderedProducts product : orderedProducts) {
            totalCost += product.getProduct().getPrice();
        }
        return totalCost;
    }

    public int getMinutesSinceIssued(){
        final long currentTime = System.currentTimeMillis();
        final long secondsFromIssued = TimeUnit.MILLISECONDS.toSeconds(currentTime - issuedTime);
        return (int)secondsFromIssued/60;
    }
}
