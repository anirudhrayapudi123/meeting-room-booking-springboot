package com.example.service;

import com.example.Dto.*;
import com.example.Exception.CustomException;
import com.example.entity.RoomSlotsEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface RoomSlot {
   // List<SlotsDto> getSlots(RoomSlotDto roomSlotDto);
    //String bookRoomSlot(RoomSlotBookDto roomSlotBookDto);
    List<Integer> getFloor() throws CustomException;
    List<String> getRooms(int roomFloor) throws CustomException;
    List<SlotsDto> getSlots(RoomSlotDto roomSlotDto) throws CustomException;
    String bookSlots(RoomSlotBookDto roomSlotBookDto);
    ResponseEntity<Object> deleteSession(String uniqueHash) throws CustomException;
    ResponseEntity<Object> myMeetings(MyMeetingsGetDto myMeetingsGetDto)throws CustomException;
    //List<MyMeetingsDto> myMeetings(MyMeetingsGetDto myMeetingsGetDto);

   String getValidation(ValidatingSlotsDto validatingSlotsDto);

   ResponseEntity<Object> updateSlots(UpdateDto updateDto) throws CustomException;

    RoomSlotsEntity getSessionDetails(String uuid) throws CustomException;
}