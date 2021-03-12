package com.jukusoft.letterbox.controller;

import com.jukusoft.letterbox.entity.ts.TSGroup;

import java.util.List;

public class ReceiverDTO {

    private long userID;
    private String username;
    private List<TSGroup> tsGroups;

    public ReceiverDTO(long userID, String username, List<TSGroup> tsGroups) {
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

    public List<TSGroup> getTsGroups() {
        return tsGroups;
    }

}
