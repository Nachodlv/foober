package webSocket;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class OrderEncoder implements Encoder.Text<OrderMessage>{
    private static Gson gson = new Gson();

    @Override
    public String encode(OrderMessage order) throws EncodeException {
        return gson.toJson(order);
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
