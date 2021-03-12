package com.jukusoft.letterbox.controller;

import com.jukusoft.authentification.jwt.account.UserAccount;
import com.jukusoft.letterbox.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/api/messages/list")
    public ResponseEntity<List<MessageDTO>> listOwnMessages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Objects.requireNonNull(authentication);

        UserAccount userAccount = (UserAccount) authentication;

        //find current userID
        long userID = userAccount.getUserID();

        List<MessageDTO> messages = userDAO.findById(userID).stream().findFirst()
                .orElseThrow().listMessages()
                .stream()
                .map(message -> new MessageDTO(message.isAnonym() ? "anonym" : message.getSender().getUsername(), message.getTitle(), message.getText()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(messages);
    }

}
