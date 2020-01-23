package com.example.Dto;

import java.time.LocalDateTime;
import java.util.Date;

public class UpdateDto {

    private String uniqueHash;

    private Date startTime;

    private Date endTime;

    private String roomSubject;

    private String roomReason;

    private String roomHost;

    private String roomName;

    private String roomType;

    private String[] participants;

    private int roomFloor;

    public int getRoomFloor() {
        return roomFloor;
    }

    public void setRoomFloor(int roomFloor) {
        this.roomFloor = roomFloor;
    }

    public String getUniqueHash() {
        return uniqueHash;
    }

    public void setUniqueHash(String uniqueHash) {
        this.uniqueHash = uniqueHash;
    }

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

    public String getRoomSubject() {
        return roomSubject;
    }

    public void setRoomSubject(String roomSubject) {
        this.roomSubject = roomSubject;
    }

    public String getRoomReason() {
        return roomReason;
    }

    public void setRoomReason(String roomReason) {
        this.roomReason = roomReason;
    }

    public String getRoomHost() {
        return roomHost;
    }

    public void setRoomHost(String roomHost) {
        this.roomHost = roomHost;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String[] getParticipants() {
        return participants;
    }

    public void setParticipants(String[] participants) {
        this.participants = participants;
    }
}
