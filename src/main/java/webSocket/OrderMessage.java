package webSocket;

public class OrderMessage {
    private boolean fromFO;
    private int id;
    private int elaborationTime;
    private String stateOrder;
    private String dgEmail;
    private double totalPrice;
    private int tippingPercentage;
    private long clientPhone;
    private String foName;
    private long foPhone;
    private String clientAddress;
    private String clientEmail;
    private String dgName;
    private long dgPhone;
    private String clientName;

    public OrderMessage(boolean fromFO, int id, int elaborationTime, String stateOrder, String dgEmail, double totalPrice, int tippingPercentage, long clientPhone, String foName, long foPhone, String clientAddress, String clientEmail, String dgName, long dgPhone, String clientName) {
        this.fromFO = fromFO;
        this.id = id;
        this.elaborationTime = elaborationTime;
        this.stateOrder = stateOrder;
        this.dgEmail = dgEmail;
        this.totalPrice = totalPrice;
        this.tippingPercentage = tippingPercentage;
        this.clientPhone = clientPhone;
        this.foName = foName;
        this.foPhone = foPhone;
        this.clientAddress = clientAddress;
        this.clientEmail = clientEmail;
        this.dgName = dgName;
        this.dgPhone = dgPhone;
        this.clientName = clientName;
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

    public void setClientPhone(long clientPhone) {
        this.clientPhone = clientPhone;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTippingPercentage() {
        return tippingPercentage;
    }

    public long getClientPhone() {
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

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public void setFoPhone(long foPhone) {
        this.foPhone = foPhone;
    }

    public long getFoPhone() {
        return foPhone;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getDgName() {
        return dgName;
    }

    public void setDgName(String dgName) {
        this.dgName = dgName;
    }

    public long getDgPhone() {
        return dgPhone;
    }

    public void setDgPhone(long dgPhone) {
        this.dgPhone = dgPhone;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}

