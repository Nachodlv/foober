package webSocket;

import org.utils.GoogleMail;
import org.utils.Utils;

import javax.mail.MessagingException;
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
        if(message.isFromFO()){
            //sendEmail(message);
        }
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
        sessions.add(session);
    }

    @OnClose
    public void onClose(Session session){
        sessions.remove(session);
    }

    private void sendEmail(OrderMessage message) throws MessagingException {
        String titleEmail = "Order received";
        String messageEmail = "You have received an order";
        GoogleMail.send("iFoober", "fooberlab1", message.getDgEmail(), titleEmail, messageEmail);
    }
}
