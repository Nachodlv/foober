package webSocket;

import org.utils.GoogleMail;
import org.utils.Utils;

import javax.mail.MessagingException;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.utils.Utils.sendEmail;

@ServerEndpoint(value = "/orderSender",
                decoders = OrderDecoder.class,
                encoders = OrderEncoder.class )
public class OrderEndPoint {

    private static Set<Session> sessions = new HashSet<>();

    @OnMessage
    public void handleMessage(OrderMessage message) throws MessagingException {
        if(message.isFromFO()){
            Utils.sendEmail(message);
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

}
