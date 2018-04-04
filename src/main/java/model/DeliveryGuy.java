package model;

import org.hibernate.type.descriptor.sql.LobTypeMappings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

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
    @Column(name = "id")
    @Lob
    private byte[] id;
    @Column(name = "meansOfTransport")
    private int meansOfTransport;

    public DeliveryGuy(String email, String name, String password, int phone, byte[] id, int meansOfTransport) {
        super(email, password, "DG");
        this.name = name;
        this.phone = phone;
        this.id = id;
        this.meansOfTransport = meansOfTransport;
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

    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    public int getMeansOfTransport() {
        return meansOfTransport;
    }

    public void setMeansOfTransport(int meansOfTransport) {
        this.meansOfTransport = meansOfTransport;
    }
}

