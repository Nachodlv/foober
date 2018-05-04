package webSocket;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class DgEncoder implements Encoder.Text<DgMessage> {

    private static Gson gson = new Gson();

    @Override
    public String encode(DgMessage dg) throws EncodeException {
        return gson.toJson(dg);
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
