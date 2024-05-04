package com.kahoot.kahoot.chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.kahoot.kahoot.users.ActiveUserManager;
import com.kahoot.kahoot.users.LiveUser;

@CrossOrigin(origins = "http://localhost:3000")
@Controller
public class ChatController {
    @Autowired
    private ActiveUserManager activeUserManager;

    // Handle sending of messages to a specific room
    @MessageMapping("/chat/{roomNumber}/sendMessage")
    @SendTo("/room/{roomNumber}")
    public ChatMessage sendMessage(
            @DestinationVariable String roomNumber,
            @Payload ChatMessage chatMessage
    ) {
        return chatMessage;
    }

    // Handle adding a user to a specific room
    @MessageMapping("/chat/{roomNumber}/addUser")
    @SendTo("/room/{roomNumber}")
    public ChatMessage addUser(
            @DestinationVariable String roomNumber,
            @Payload ChatMessage chatMessage,
            SimpMessageHeaderAccessor headerAccessor
    ) {
        LiveUser user = chatMessage.getSender();
        if (activeUserManager.userExists(roomNumber, user)) {
            // User already exists in the room\
            return ChatMessage.builder()
                    .type(MessageType.ERROR)
                    .content("User already exists in the room")
                    .sender(user)
                    .build();
        }
        activeUserManager.addUser(roomNumber,user);
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("user", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("roomNumber", roomNumber);
        return chatMessage;
    }

    // updateUser imageUrl
    @MessageMapping("/chat/{roomNumber}/updateUser")
    @SendTo("/room/{roomNumber}")
    public ChatMessage updateUser(
            @DestinationVariable String roomNumber,
            @Payload ChatMessage chatMessage
    ) {
        activeUserManager.updateUser(roomNumber,chatMessage.getSender());
        return chatMessage;
    }


    // get all active users

    @MessageMapping("/chat/{roomNumber}/getUsers")
    @SendTo("/room/{roomNumber}/activeUsers")
    public List<LiveUser> getActiveUsers(@DestinationVariable String roomNumber) {
        System.out.println("Getting active users");
        return activeUserManager.getUsers(roomNumber);
    }

    // Handle removing a user from a specific room
    @MessageMapping("/chat/{roomNumber}/removeUser")
    @SendTo("/room/{roomNumber}/activeUsers")
    public List<LiveUser> removeUser(
            @DestinationVariable String roomNumber,
            @Payload LiveUser user
    ) {
        System.out.println("Removing user");
        activeUserManager.removeUser(roomNumber, user);
        System.out.println(activeUserManager.getUsers(roomNumber));
        // disconnect user's websocket connection


        return activeUserManager.getUsers(roomNumber);
    }



}
