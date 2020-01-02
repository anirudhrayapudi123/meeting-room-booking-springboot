package com.example.Dto;

import java.time.LocalDateTime;

public class MyMeetingsGetDto {

    private String participant;

    private LocalDateTime startTime;

    public String getParticipant() {
        return participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
}
