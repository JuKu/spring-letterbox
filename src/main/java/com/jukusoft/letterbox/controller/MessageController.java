package com.jukusoft.letterbox.controller;

import com.jukusoft.authentification.jwt.account.UserAccount;
import com.jukusoft.letterbox.dao.UserDAO;
import com.jukusoft.letterbox.entity.message.MessageEntity;
import com.jukusoft.letterbox.entity.user.UserEntity;
import com.jukusoft.letterbox.utils.UserHelperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
public class MessageController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserHelperService userHelperService;

    @GetMapping("/api/messages/list")
    public ResponseEntity<List<MessageDTO>> listOwnMessages() {
        List<MessageDTO> messages = userHelperService.getCurrentUser()
                .listMessages()
                .stream()
                .map(message -> new MessageDTO(message.isAnonym() ? "anonym" : message.getSender().getUsername(), message.getTitle(), message.getText()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(messages);
    }

    @Transactional
    @PostMapping("/api/messages/write")
    public void writeMessage(@RequestBody WriteMessageRequest request) {
        UserEntity currentUser = userHelperService.getCurrentUser();
        UserEntity receiver = userDAO.findById(request.getReceiverID()).orElseThrow();

        MessageEntity message = new MessageEntity(receiver, currentUser, request.isAnonym(), HtmlUtils.htmlEscape(request.getTitle(), "UTF-8"), HtmlUtils.htmlEscape(request.getText(), "UTF-8"));
        receiver.addMessage(message);
        userDAO.save(receiver);
    }

}
