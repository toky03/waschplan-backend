package ch.bfh.control;


import ch.bfh.dto.NotifyMessage;
import ch.bfh.dto.NotifyMessageDecoder;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint(value = "/subscribe/{sessionId}", encoders = NotifyMessageDecoder.class)
@ApplicationScoped
public class NotifierService {

    Map<UUID, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("sessionId") String sessionId) {
        sessions.put(UUID.fromString(sessionId), session);
    }

    @OnClose
    public void onClose(Session session, @PathParam("sessionId") String sessionId) {
        sessions.remove(UUID.fromString(sessionId));
    }

    @OnError
    public void onError(Session session, @PathParam("sessionId") String sessionId, Throwable throwable) {
        sessions.remove(UUID.fromString(sessionId));
    }

    public void broadcast(NotifyMessage message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result ->  {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        });
    }
}
