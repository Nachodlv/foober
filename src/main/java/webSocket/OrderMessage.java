package webSocket;

public class OrderMessage {
    private boolean fromFO;
    private int elaborationTime;
    private String stateOrder;
    private String dgEmail;
    private double totalPrice;
    private int tippingPercentage;
    private String clientEmail;

    public OrderMessage(boolean fromFO, int elaborationTime, String status, String dgEmail, double totalPrice, int tippingPercentage, String clientEmail) {
        this.fromFO = fromFO;
        this.elaborationTime = elaborationTime;
        this.stateOrder = status;
        this.dgEmail = dgEmail;
        this.totalPrice = totalPrice;
        this.tippingPercentage = tippingPercentage;
        this.clientEmail = clientEmail;
    }

    public void setFromFO(boolean fromFO) {
        this.fromFO = fromFO;
    }

    public void setElaborationTime(int elaborationTime) {
        this.elaborationTime = elaborationTime;
    }

    public void setStateOrder(String status) {
        this.stateOrder = status;
    }

    public void setDgEmail(String dgEmail) {
        this.dgEmail = dgEmail;
    }

    public boolean isFromFO() {
        return fromFO;
    }

    public int getElaborationTime() {
        return elaborationTime;
    }

    public String getStateOrder() {
        return stateOrder;
    }

    public String getDgEmail() {
        return dgEmail;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTippingPercentage(int tippingPercentage) {
        this.tippingPercentage = tippingPercentage;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTippingPercentage() {
        return tippingPercentage;
    }

    public String getClientEmail() {
        return clientEmail;
    }
}
