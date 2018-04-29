package model;

import org.hibernate.type.descriptor.sql.LobTypeMappings;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Gonzalo de Achaval
 * @author Ignatius de la Vega
 */

@Entity
@Table(name = "deliveryGuy")
public class DeliveryGuy extends UserAccount {
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private int phone;
    @Column(name = "meansOfTransport")
    private int meansOfTransport;
    @OneToMany(mappedBy = "deliveryGuy", fetch = FetchType.EAGER)
    private Set<Order> orders;
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private StateDG state;

    public DeliveryGuy(String email, String name, String password, int phone, int meansOfTransport) {
        super(email, password, "DG");
        this.name = name;
        this.phone = phone;
        this.meansOfTransport = meansOfTransport;
        orders = new HashSet<>();
        state = StateDG.OFFLINE;
    }

    public DeliveryGuy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
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
}

