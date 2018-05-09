package webSocket;

public class OrderMessage {
    private boolean fromFO;
    private int elaborationTime;
    private String status;
    private String dgEmail;


    public OrderMessage(boolean fromFO, int elaborationTime, String status, String dgEmail) {
        this.fromFO = fromFO;
        this.elaborationTime = elaborationTime;
        this.status = status;
        this.dgEmail = dgEmail;
    }

    public void setFromFO(boolean fromFO) {
        this.fromFO = fromFO;
    }

    public void setElaborationTime(int elaborationTime) {
        this.elaborationTime = elaborationTime;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public String getDgEmail() {
        return dgEmail;
    }
}
