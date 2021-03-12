package com.jukusoft.letterbox.controller;

import com.jukusoft.letterbox.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ReceiverController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/api/receiver/list")
    public ResponseEntity<List<ReceiverDTO>> listReceiver() {
        List<ReceiverDTO> list = userDAO.findAll().stream()
                .map(userEntity -> new ReceiverDTO(userEntity.getUserID(), userEntity.getUsername(), userEntity.listTSGroups()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

}
