package webSocket;

import com.google.gson.Gson;
import model.DeliveryGuy;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

public class DgDecoder implements Decoder.Text<DgMessage> {

    private static Gson gson = new Gson();

    @Override
    public DgMessage decode(String s) throws DecodeException {
        return gson.fromJson(s, DgMessage.class);
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        // Custom initialization logic
    }

    @Override
    public void destroy() {
        // Close resources
    }
}