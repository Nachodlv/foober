package webSocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/orderSender",
                decoders = OrderDecoder.class,
                encoders = OrderEncoder.class )
public class OrderEndPoint {

    private static Set<Session> sessions = new HashSet<>();

    @OnMessage
    public void handleMessage(OrderMessage message){
        System.out.println("Message sent ==>" + message);
        for(Session session: sessions){
            try {
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    @OnOpen
    public void onOpen(Session session){
        System.out.println("Session opened ==>");
        sessions.add(session);
    }

    @OnClose
    public void onClose(){
        System.out.println("Session closed =>");
    }
}
