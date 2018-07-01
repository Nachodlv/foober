package webSocket;

import org.utils.Utils;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ServerEndpoint(value = "/orderSender/{email}",
        decoders = OrderDecoder.class,
        encoders = OrderEncoder.class)
public class OrderEndPoint {

    private static Map<Session, String> sessions = new HashMap<>();

    @OnMessage
    public void handleMessage(OrderMessage message, @PathParam("email") String email) {
        if (message.isFromFO()) {
            if(message.getStateOrder().equals("CANCELLED")){
                Utils.sendEmailDGCancelled(message);
            } else if (message.getStateOrder().equals("WAITING")){
                Utils.sendEmailDG(message);
            }
        } else if (message.isAccepted()) {
            Utils.sendEmailClient(message);
        }
        for (Map.Entry<Session, String> session : sessions.entrySet()) {
            try {
                if(session.getValue().equals(email))
                    session.getKey().getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("email") String email) {
        sessions.put(session, email);
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }

    private void sendMessageToDG(String email, OrderMessage message){

    }
}
