package demo.day01.repository;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // 连接建立后的逻辑，如发送欢迎消息
        session.sendMessage(new TextMessage("Welcome to the WebSocket server!"));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 处理接收到的文本消息
        String payload = message.getPayload();
        // ... 处理消息的逻辑，如解析、存储或转发
        // 发送响应消息
        session.sendMessage(new TextMessage("Received message: " + payload));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 连接关闭后的逻辑，如清理资源
    }

    @Override
    public boolean supportsPartialMessages() {
        // 是否支持分片消息，默认为true
        return true;
    }
}
