package webSocket;

public class OrderMessage {
    private boolean fromFO;
    private int id;
    private int elaborationTime;
    private String stateOrder;
    private String dgEmail;
    private double totalPrice;
    private int tippingPercentage;
    private String clientPhone;
    private String foName;

    public OrderMessage(boolean fromFO, int id, int elaborationTime, String stateOrder, String dgEmail, double totalPrice, int tippingPercentage, String clientPhone, String foName) {
        this.fromFO = fromFO;
        this.id = id;
        this.elaborationTime = elaborationTime;
        this.stateOrder = stateOrder;
        this.dgEmail = dgEmail;
        this.totalPrice = totalPrice;
        this.tippingPercentage = tippingPercentage;
        this.clientPhone = clientPhone;
        this.foName = foName;
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

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTippingPercentage() {
        return tippingPercentage;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public String getFoName() {
        return foName;
    }

    public void setFoName(String foName) {
        this.foName = foName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }
}
