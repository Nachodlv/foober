package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="franchiseOwner")
public class FranchiseOwner {
    @Id
    @Column(name="email")
    private String Email;
    @Column(name="name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "phone")
    private int phone;
    @Column(name="address")
    private String address;
    @Column(name="menuId")
    private int menuId;
    @Column(name = "tippingPercentage")
    private int tippingPercentage;


    public String getEmail() {
        return Email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

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

    public void setEmail(String email) {
        Email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
