package com.example.Dto;

import java.sql.Date;


public class MyMeetingsDto {

    private Date startTime;

    private Date endTime;

    private String roomName;

    private String roomHost;

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    private String manager;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomHost() {
        return roomHost;
    }

    public void setRoomHost(String roomHost) {
        this.roomHost = roomHost;
    }

}
