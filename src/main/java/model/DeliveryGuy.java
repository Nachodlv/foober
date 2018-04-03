package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Gonzalo de Achaval
 * @author Ignatius de la Vega
 */

@Entity
@Table(name = "deliveryGuy")
public class DeliveryGuy extends UserAccount {
    //@Id
    //@Column(name="email")
    //private String Email;
    @Column(name = "name")
    private String name;
    //@Column(name = "password")
    //private String password;
    @Column(name = "phone")
    private int phone;
    @Column(name = "id")
    private String id;
    @Column(name = "meansOfTransport")
    private int meansOfTransport;

    public DeliveryGuy(String email, String name, String password, int phone, String id, int meansOfTransport) {
        super(email, password, "DG");
        //Email = email;
        this.name = name;
        //this.password = password;
        this.phone = phone;
        this.id = id;
        this.meansOfTransport = meansOfTransport;

    }

    public DeliveryGuy() {
    }

    //public String getEmail() {
    //    return Email;
    //}

    //public void setEmail(String email) {
    //    Email = email;
    //}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //public String getPassword() {
    //    return password;
    //}

    //public void setPassword(String password) {
    //    this.password = password;
    //}

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMeansOfTransport() {
        return meansOfTransport;
    }

    public void setMeansOfTransport(int meansOfTransport) {
        this.meansOfTransport = meansOfTransport;
    }
}

