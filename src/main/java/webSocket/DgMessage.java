package webSocket;

public class DgMessage {
    private String name;
    private int meansOfTransport;
    private int phone;
    private String state;
    private String email;

    public DgMessage(String name, int meansOfTransport, int phone, String state, String email) {
        this.name = name;
        this.meansOfTransport = meansOfTransport;
        this.phone = phone;
        this.state = state;
        this.email = email;
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
}
