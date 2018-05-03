package webSocket;


import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/dgOnline")
public class DgEndPoint {

    @OnMessage
    public String message(String message) {

        return "hola desde el servidor el mensaje es :" + message;
    }
}
