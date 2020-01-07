package com.example.entity;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="session_details")
public class RoomSlotsEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name ="UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "room_unique_hash")
    private String uniqueHash;

    @Column(name = "room_start_date_time")
    private Date startTime;

    @Column(name = "room_end_date_time")
    private Date endTime;

    @Column(name = "room_subject")
    private String roomSubject;



    @Column(name = "room_reason")
    private String roomReason;

    @Column(name = "room_host")
    private String roomHost;

    @Column(name="room_name")
    private String roomName;

    @Column(name = "room_type")
    private String roomType;


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

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getUniqueHash() {
        return uniqueHash;
    }

    public void setUniqueHash(String uniqueHash) {
        this.uniqueHash = uniqueHash;
    }


    public String getRoomReason() {
        return roomReason;
    }

    public void setRoomReason(String roomReason) {
        this.roomReason = roomReason;
    }

    public String getRoomSubject() {
        return roomSubject;
    }

    public void setRoomSubject(String roomSubject) {
        this.roomSubject = roomSubject;
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

}



