package webSocket;

import org.utils.Utils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/orderSender/{email}",
        decoders = OrderDecoder.class,
        encoders = OrderEncoder.class)
public class OrderEndPoint {

    private static Set<Session> sessions = new HashSet<>();

    @OnMessage
    public void handleMessage(OrderMessage message) {
        if (message.isFromFO()) {
            Utils.sendEmail(message, true);
        } else if (message.getStateOrder().equals("DELIVERING")) {
            Utils.sendEmail(message, false);
        }
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("email") String email) {
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

}
