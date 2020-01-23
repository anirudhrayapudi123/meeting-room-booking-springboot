package com.example.controller;

import com.example.Dto.*;
import com.example.Exception.CustomException;
import com.example.repository.CreateSlotRepository;
import com.example.service.RoomSlot;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


@RestController
public class RoomSlotController {

    @Autowired
     private RoomSlot roomSlot;

    @Autowired
    CreateSlotRepository createSlotRepository;

    @CrossOrigin(origins="http://localhost:8080")
    @RequestMapping(value = "/floors",method = RequestMethod.POST)
    public ResponseEntity<Object> getFloors() throws CustomException{
        return ResponseEntity.status(200).body(roomSlot.getFloor());
    }
    @CrossOrigin(origins="http://localhost:8080")
    @RequestMapping(value="/roomnames/{roomFloor}",method = RequestMethod.POST)
    public ResponseEntity<Object> getRooms(@PathVariable int roomFloor) throws CustomException{
        return ResponseEntity.status(200).body(roomSlot.getRooms(roomFloor));
    }
    @CrossOrigin(origins="http://localhost:8080")
    @RequestMapping(value = "/slots",method=RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<Object> getSlots(@RequestBody RoomSlotDto roomSlotDto) throws CustomException{
        return ResponseEntity.status(200).body(roomSlot.getSlots(roomSlotDto));
    }
    @CrossOrigin(origins="http://localhost:8080")
    @RequestMapping(value = "bookSlots",method=RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<Object> bookSlots(@RequestBody RoomSlotBookDto roomSlotBookDto) throws ParseException {
        return ResponseEntity.status(200).body(roomSlot.bookSlots(roomSlotBookDto));
    }
    @CrossOrigin(origins="http://localhost:8080")
    @RequestMapping(value = "deleteSession/{uuid}",method = RequestMethod.POST)
    public ResponseEntity<Object> deleteSession(@PathVariable String uuid)throws CustomException{
        return ResponseEntity.status(200).body(roomSlot.deleteSession(uuid));
     }

     @RequestMapping(value = "myMeetings",method = RequestMethod.POST)
     public ResponseEntity<Object> myMeetings(@RequestBody MyMeetingsGetDto myMeetingsGetDto) throws CustomException, ParseException {
        return ResponseEntity.status(200).body(roomSlot.myMeetings(myMeetingsGetDto));
     }

//    @CrossOrigin(origins="http://localhost:8080")
//    @RequestMapping(value = "/validatingSlots",method=RequestMethod.POST,consumes = "application/json")
//    public ResponseEntity<Object> getValidation(@RequestBody ValidatingSlotsDto validatingSlotsDto){
//        return ResponseEntity.status(200).body(roomSlot.getValidation(validatingSlotsDto));
   // }
    @CrossOrigin(origins="http://localhost:8080")
    @RequestMapping(value = "/update",method = RequestMethod.POST,consumes = "application/json")
    public ResponseEntity<Object> updateSlots(@RequestBody UpdateDto updateDto) throws CustomException{
        return ResponseEntity.status(200).body(roomSlot.updateSlots(updateDto));
    }
    @CrossOrigin(origins="http://localhost:8080")
    @RequestMapping(value = "/sessionDetails/{uniqueHash}",method = RequestMethod.POST)
    public ResponseEntity<Object> getSessionDetails(@PathVariable String uniqueHash) throws CustomException{
        return ResponseEntity.status(200).body(roomSlot.getSessionDetails(uniqueHash));
    }

    @CrossOrigin(origins="http://localhost:8080")
    @RequestMapping(value = "/test/{startTime}/{endTime}/{roomName}",method = RequestMethod.GET)
    public ResponseEntity<Object> validatingSlots(@PathVariable String startTime, @PathVariable String endTime, @PathVariable String roomName) throws CustomException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date startDateTime=sdf.parse(startTime.trim());
        Date endDateTime=sdf.parse(endTime.trim());
        return ResponseEntity.status(200).body(roomSlot.findRoomSlots(startDateTime,endDateTime,roomName));
    }
}


