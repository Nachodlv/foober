package model;

import javax.persistence.*;

@Entity
@Table (name = "product")
public class Product {
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private double price;
    @Column(name = "image")
    @Lob
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "FO_email", nullable = false)
    private FranchiseOwner franchiseOwner;

    public Product(){}

    public Product(String name, double price, byte[] image, FranchiseOwner franchiseOwner) {
        this.name = name;
        this.price = price;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
