package demo.day01.domain;

import demo.day01.repository.MyWebSocketHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new MyWebSocketHandler(), "/new-websocket-endpoint")
//                .setAllowedOrigins("https://localhost:8080") // 允许所有起源，出于安全考虑，您可以限制为特定的起源
//                .addInterceptors(new MyWebSocketInterceptor()) // 可选：添加拦截器以处理额外的逻辑，如身份验证
                .withSockJS(); // 可选：如果您想使用SockJS作为WebSocket的备选方案，以支持不支持WebSocket的浏览器
    }
}
