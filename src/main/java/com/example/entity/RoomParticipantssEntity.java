package com.example.entity;

import java.io.Serializable;

public class RoomParticipantssEntity implements Serializable{

    private String uniqueHash;

    private String participant;
    public RoomParticipantssEntity(){}

    public RoomParticipantssEntity(String uniqueHash, String participant) {
        this.uniqueHash = uniqueHash;
        this.participant = participant;
    }

    public String getUniqueHash() {
        return uniqueHash;
    }



    public void setUniqueHash(String uniqueHash) {
        this.uniqueHash = uniqueHash;
    }

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }
}
