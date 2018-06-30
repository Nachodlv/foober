package webSocket;

import javafx.geometry.Pos;

public class DgMessage {
    private String name;
    private int meansOfTransport;
    private int phone;
    private String state;
    private String email;
    private int ratingQuantity;
    private double rating;
    private Position position;

    public DgMessage(String name, int meansOfTransport, int phone, String state, String email, int ratingQuantity,
                     double rating, Position position) {
        this.name = name;
        this.meansOfTransport = meansOfTransport;
        this.phone = phone;
        this.state = state;
        this.email = email;
        this.rating = rating;
        this.ratingQuantity = ratingQuantity;
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeansOfTransport(int meansOfTransport) {
        this.meansOfTransport = meansOfTransport;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public int getMeansOfTransport() {
        return meansOfTransport;
    }

    public int getPhone() {
        return phone;
    }

    public String getState() {
        return state;
    }

    public int getRatingQuantity() {
        return ratingQuantity;
    }

    public void setRatingQuantity(int ratingQuantity) {
        this.ratingQuantity = ratingQuantity;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {

        return position;
    }
}
