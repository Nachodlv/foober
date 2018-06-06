package model;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "franchiseOwner")
public class FranchiseOwner extends UserAccount {
    @Expose
    @Column(name = "name")
    private String name;
    @Expose
    @Column(name = "phone")
    private long phone;
    @Expose
    @Column(name = "address")
    private String address;
    @Column(name = "menuId")
    private int menuId;
    @Expose
    @Column(name = "tippingPercentage")
    private int tippingPercentage;
    @OneToMany(mappedBy = "franchiseOwner", fetch = FetchType.EAGER)
    private Set<Client> clients;
    @OneToMany(mappedBy = "franchiseOwner", fetch = FetchType.EAGER)
    private Set<Product> products;
    @OneToMany(mappedBy = "franchiseOwner", fetch = FetchType.EAGER)
    private Set<Order> orders;


    public FranchiseOwner() {
        products = new HashSet<>();
        clients = new HashSet<>();
        orders = new HashSet<>();
    }

    public FranchiseOwner(String email, String name, String password, long phone, String address, int menuId, int tippingPercentage) {
        super(email, password, "FO");
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.menuId = menuId;
        this.tippingPercentage = tippingPercentage;
        products = new HashSet<>();
        clients = new HashSet<>();
        orders = new HashSet<>();
    }

    public FranchiseOwner(String email, String name, String password, long phone, String address) {
        super(email, password, "FO");
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.tippingPercentage = 10;
        menuId = 1;
        products = new HashSet<>();
        clients = new HashSet<>();
        orders = new HashSet<>();
    }


    public String getName() {
        return name;
    }

    public long getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public int getMenuId() {
        return menuId;
    }

    public int getTippingPercentage() {
        return tippingPercentage;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public void setTippingPercentage(int tippingPercentage) {
        this.tippingPercentage = tippingPercentage;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
