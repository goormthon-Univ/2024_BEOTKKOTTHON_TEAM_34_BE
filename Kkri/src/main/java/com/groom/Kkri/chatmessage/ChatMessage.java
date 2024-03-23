//package com.groom.Kkri.chatmessage;
//
//import com.groom.Kkri.entity.BaseTimeEntity;
//import jakarta.persistence.EntityListeners;
//import jakarta.persistence.Id;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.bson.types.ObjectId;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//import java.time.LocalDateTime;
//
//@Document
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class)
//public class ChatMessage {
//    @Id
//    private String id;
//    private String chatRoomId;
//    private String senderId;
//    private String recipientId;
//    private String content;
//
//    @CreatedDate
//    private LocalDateTime createdAt;
//}
