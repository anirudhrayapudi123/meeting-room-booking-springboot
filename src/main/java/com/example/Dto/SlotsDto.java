package com.example.Dto;


import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.TemporalType;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SlotsDto {

    private java.util.Date roomStartTime;

    private java.util.Date roomEndTime;
    private String roomHost;

    public java.util.Date getRoomStartTime() {
        return roomStartTime;
    }

    public void setRoomStartTime(java.util.Date roomStartTime) {
        this.roomStartTime = roomStartTime;
    }

    public java.util.Date getRoomEndTime() {
        return roomEndTime;
    }

    public void setRoomEndTime(java.util.Date roomEndTime) {
        this.roomEndTime = roomEndTime;
    }

    public String getRoomHost() {
        return roomHost;
    }

    public void setRoomHost(String roomHost) {
        this.roomHost = roomHost;
    }
}
