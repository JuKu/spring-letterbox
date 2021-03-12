package com.jukusoft.letterbox.controller;

import com.jukusoft.letterbox.entity.ts.TSGroupEntity;

import java.util.List;

public class ReceiverDTO {

    private long userID;
    private String username;
    private List<TSGroupEntity> tsGroups;

    public ReceiverDTO(long userID, String username, List<TSGroupEntity> tsGroups) {
        this.userID = userID;
        this.username = username;
        this.tsGroups = tsGroups;
    }

    public long getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public List<TSGroupEntity> getTsGroups() {
        return tsGroups;
    }

}
