package model;

import javax.persistence.*;

@Entity
@Table(name = "orderedProduct")
public class OrderedProducts {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "comment")
    private String comment;

    @Column(name = "quantity")
    private int quantity;

    public OrderedProducts() {
    }

    public OrderedProducts(Product product, Order order, String comment, int quantity) {
        this.product = product;
        this.order = order;
        this.comment = comment;
        this.quantity = quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Product getProduct() {

        return product;
    }

    public Order getOrder() {
        return order;
    }

    public String getComment() {
        return comment;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
