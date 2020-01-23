package com.example.Dto;

import java.time.LocalDateTime;
import java.util.Date;

public class Mapping {

    private String uniqueHash;

    private String participant;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    private Date startDate;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

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
