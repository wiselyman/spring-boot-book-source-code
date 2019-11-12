package top.wisely.learningwebsocketreactive.handler;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

public class HelloHandler implements WebSocketHandler {
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.send(session.receive()
                .map(msg -> "Hello:" + msg.getPayloadAsText())
                .map(session::textMessage)
        );
    }
}
