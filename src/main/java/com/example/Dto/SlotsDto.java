package com.example.Dto;


import org.springframework.data.jpa.repository.Temporal;

import javax.persistence.TemporalType;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class SlotsDto {

    private Time roomStartTime;

    private Time roomEndTime;
    private String roomHost;


    public java.util.Date getRoomStartTime() {
        return roomStartTime;
    }

    public void setRoomStartTime(Time roomStartTime) {
        this.roomStartTime = roomStartTime;
    }

    public Time getRoomEndTime() {
        return roomEndTime;
    }

    public void setRoomEndTime(Time roomEndTime) {
        this.roomEndTime = roomEndTime;
    }

    public String getRoomHost() {
        return roomHost;
    }

    public void setRoomHost(String roomHost) {
        this.roomHost = roomHost;
    }
}
