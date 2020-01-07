package com.example.Dto;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class MyMeetingsGetDto {

    private String participant;

    private Date startDate;

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
