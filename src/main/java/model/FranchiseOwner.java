package model;

import org.hibernate.mapping.List;
import org.hibernate.mapping.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "franchiseOwner")
public class FranchiseOwner extends UserAccount {
    //@Id
    //@Column(name="email")
    //private String Email;
    @Column(name = "name")
    private String name;
    //@Column(name = "password")
    //private String password;
    @Column(name = "phone")
    private int phone;
    @Column(name = "address")
    private String address;
    @Column(name = "menuId")
    private int menuId;
    @Column(name = "tippingPercentage")
    private int tippingPercentage;
    @OneToMany(mappedBy = "franchiseOwner")
    private Set<Product> products;
    public FranchiseOwner() {
        products = new HashSet<>();
    }

    public FranchiseOwner(String email, String name, String password, int phone, String address, int menuId, int tippingPercentage) {
        super(email, password, "FO");
        this.name = name;
        //this.password = password;
        this.phone = phone;
        this.address = address;
        this.menuId = menuId;
        this.tippingPercentage = tippingPercentage;
        products = new HashSet<>();
    }

    public FranchiseOwner(String email, String name, String password, int phone, String address) {
        super(email, password, "FO");
        this.name = name;
        //this.password = password;
        this.phone = phone;
        this.address = address;
        this.menuId = menuId;
        this.tippingPercentage = 10;
        menuId = 1;
        products = new HashSet<>();
    }
    //public String getEmail() {
    //    return Email;
    //}

    public String getName() {
        return name;
    }

    //public String getPassword() {
    //    return password;
    //}

    public int getPhone() {
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

    //public void setEmail(String email) {
    //    Email = email;
    //}

    public void setName(String name) {
        this.name = name;
    }

    //public void setPassword(String password) {
    //    this.password = password;
    //}

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

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        products.add(product);
    }
}
