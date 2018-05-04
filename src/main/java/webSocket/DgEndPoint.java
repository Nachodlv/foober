package webSocket;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/dgOnline",
        decoders = DgDecoder.class,
        encoders = DgEncoder.class)
public class DgEndPoint {

    private Set<Session> sessions = new HashSet<>();

    @OnMessage
    public void handleMessage(DgMessage message) {
        System.out.println("Message sent ==>" + message.getName());
        for (Session session : sessions) {
            try {
                session.getBasicRemote().sendObject(message);
            } catch (IOException | EncodeException e) {
                e.printStackTrace();
            }
        }
    }

    @OnOpen
    public void open(Session session) {
        System.out.println("Session opened ==>");
        sessions.add(session);
    }

    @OnClose
    public void close(Session session) {
        System.out.println("Session closed ==>");
        sessions.remove(session);
    }

    @OnError
    public void onError(Throwable e) {
        System.out.println(e.getMessage());
        e.printStackTrace();
    }
}
