package com.groom.Kkri.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.List;
import java.util.Map;
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketConfigurer {
//
//    private final SwoomiWebSocketHandler chatHandler;
//
//    @Override
//    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry){
//
//        registry.addHandler(chatHandler, "ws/chat").setAllowedOrigins("*");
//    }
//}

//@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
//
//    @Override
//    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        //메세지를 구독하는 요청의 prefix는 /sub으로 시작하도록
//        registry.enableSimpleBroker("/sub");
//        //메세지를 발행하는 요청의 prefix는 /pub으로 시작하도록
//        registry.setApplicationDestinationPrefixes("/pub");
////        //메세지 전송 prefix
//        registry.setUserDestinationPrefix("/user");
//    }
//
//    @Override
//    public void registerStompEndpoints(StompEndpointRegistry registry) {
//        // 클라는 /ws엔드 포인트를 통해 서버와 연결을 설정함
//        // socketjs로 websocket을 대체하는 기능을 활성화
//        registry.addEndpoint("/ws/chat")
//                .withSockJS();
//    }
//
//    @Override
//    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
//        //메세지를 객체로 변환하고, 객체를 메세지로 변환하는데 사용됨
//
//        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
//        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON);
//        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
//        converter.setObjectMapper(new ObjectMapper());
//        converter.setContentTypeResolver(resolver);
//        messageConverters.add(converter);
//
//        //default로 사용하지 않겠음
//        return false;
//    }
//}

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/learnCodeWithSankalp")
                .setAllowedOriginPatterns("http://localhost:8070")
                .withSockJS();
        registry.addEndpoint("/learnCodeWithSankalp")
                .setAllowedOriginPatterns("http://localhost:8070");

    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

}
