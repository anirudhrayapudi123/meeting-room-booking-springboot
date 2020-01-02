package com.example.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(RoomParticipantssEntity.class)
@Table(name = "session_participants")
public class RoomParticipantEntity {

    @Id
    @Column(name = "room_unique_hash")
    private String uniqueHash;

    @Id
    @Column(name="room_participant")
    private String participant;

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
