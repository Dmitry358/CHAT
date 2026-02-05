package mitiok.ddns.net.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class ChatHandler extends TextWebSocketHandler {

  private static final Logger log = LoggerFactory.getLogger(ChatHandler.class);
  private final Set<WebSocketSession> sessions =
    Collections.synchronizedSet(new HashSet<>());

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    sessions.add(session);
    log.info("CHAT CONNECT id={} ip={}", session.getId(), session.getRemoteAddress());
  }

  @Override
  protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    synchronized (sessions) {
      for (WebSocketSession s : sessions) {
        if (s.isOpen()) {
          s.sendMessage(message);
        }
      }
    }
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status) {
    sessions.remove(session);
    log.info("CHAT DISCONNECT id={}", session.getId());
  }
}
