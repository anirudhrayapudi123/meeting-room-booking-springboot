package com.example.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@IdClass(RoomParticipantssEntity.class)
@Table(name = "participants")
public class RoomParticipantEntity {

    @Id
    @Column(name = "room_unique_hash")
    private String uniqueHash;

    @Id
    @Column(name="room_participant")
    private String participant;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }


    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "is_manager")
    private String role;

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
