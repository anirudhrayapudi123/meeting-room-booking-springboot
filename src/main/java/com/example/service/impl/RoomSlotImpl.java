package com.example.service.impl;

import com.example.Dto.*;
import com.example.Exception.CustomException;
import com.example.entity.*;
import com.example.repository.*;
import com.example.service.RoomSlot;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.TypeToken;

import javax.transaction.Transactional;
import java.lang.reflect.Type;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RoomSlotImpl implements RoomSlot {

    @Autowired
    RoomSlotRepository roomSlotRepository;
    @Autowired
    SlotsRepository slotsRepository;
    @Autowired
    CreateSlotRepository createSlotRepository;
    @Autowired
    RoomParticiptantsEntryRepository roomParticiptantsEntryRepository;
    RoomParticipantEntity roomParticipantEntity;
    private ModelMapper modelMapper = new ModelMapper();

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public List<Integer> getFloor() throws CustomException {
        List<Integer> floors = roomSlotRepository.findDistinctFloor();
        if(floors==null){
            throw new CustomException("floors Not available",HttpStatus.NO_CONTENT);
        }
        return floors;
    }

    @Override
    public List<String> getRooms(int roomFloor) throws CustomException{
        List<String> rooms = roomSlotRepository.findAllByFloor(roomFloor);
        if(rooms==null){
            throw new CustomException("Rooms not available",HttpStatus.NO_CONTENT);
        }
        return rooms;
    }

    @Override
    public List<SlotsDto> getSlots(RoomSlotDto roomSlotDto) throws CustomException {
       // List<RoomSlotEntity> slots = slotsRepository.findAllByStartTimeAndRoomName(roomSlotDto.getDate(), roomSlotDto.getRoomName());
        List<RoomParticipantEntity> hash=roomParticiptantsEntryRepository.findByStartDate(roomSlotDto.getStartDate());
        Set<String> st=new HashSet<>();
        for(RoomParticipantEntity room:hash){
            String str=room.getUniqueHash();
            st.add(str);
        }

        List<RoomSlotsEntity> slots=new ArrayList<RoomSlotsEntity>();
         for(String str1:st){
             System.out.println(str1);
            RoomSlotsEntity slot=(createSlotRepository.findAllByUniqueHashAndRoomName(str1,roomSlotDto.getRoomName()));
            slots.add(slot);
         }

        Type listType = new TypeToken<List<SlotsDto>>() {}.getType();
        List<SlotsDto> SlotDTOList = modelMapper.map(slots, listType);
        if(SlotDTOList==null){
            throw new CustomException("Slots Not Available",HttpStatus.NO_CONTENT);
        }
        return SlotDTOList;
    }

    @Override
    public String bookSlots(RoomSlotBookDto roomSlotBookDto) throws ParseException {
      //  List<Object> roomSlotEntity = createSlotRepository.findRoomSlot(roomSlotBookDto.getStartTime(), roomSlotBookDto.getEndTime(),roomSlotBookDto.getRoomName());
       // if (roomSlotEntity.isEmpty()){

            RoomSlotsEntity roomSlotsEntity1 = modelMapper.map(roomSlotBookDto,RoomSlotsEntity.class);
            //roomSlotsEntity1.setStartDate(roomSlotBookDto.getStartTime());
            createSlotRepository.save(roomSlotsEntity1);
            String[] participants=roomSlotBookDto.getParticipants();
            String uuid=roomSlotsEntity1.getUniqueHash();
            Mapping mapping1=new Mapping();
            mapping1.setParticipant(roomSlotBookDto.getRoomHost());
            mapping1.setStartDate(roomSlotBookDto.getStartTime());
            mapping1.setUniqueHash(uuid);
            mapping1.setRole("manager");
            RoomParticipantEntity roomParticipantEntity1=modelMapper.map(mapping1,RoomParticipantEntity.class);
            roomParticiptantsEntryRepository.save(roomParticipantEntity1);
            for(String str:participants){
                RoomParticipantEntity roomParticipantEntity=null;
                Mapping mapping=new Mapping();
                mapping.setParticipant(str);
               mapping.setUniqueHash(uuid);
               mapping.setStartDate(roomSlotBookDto.getStartTime());
               mapping.setRole("participant");
           roomParticipantEntity= modelMapper.map(mapping, RoomParticipantEntity.class);
               roomParticiptantsEntryRepository.save(roomParticipantEntity);
            }
          return "booked Successfully";
        }
//        else{
//           return "slots occupied" ;
//        }
//    }

    @Transactional
    @Override
    public ResponseEntity<Object> deleteSession(String uniqueHash) throws CustomException{
        RoomSlotsEntity roomSlotsEntity = createSlotRepository.findByUniqueHash(uniqueHash);
        if (roomSlotsEntity == null) {
            throw new CustomException("This Session does not exist", HttpStatus.NO_CONTENT);
        } else if ((createSlotRepository.removeByUniqueHash(uniqueHash) != 0)&&(roomParticiptantsEntryRepository.removeByUniqueHash(uniqueHash)!=0) ) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Session Deleted Succesfully");
        }
        throw new CustomException("This Session does not exist", HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Object> myMeetings(MyMeetingsGetDto myMeetingsGetDto) throws CustomException, ParseException {
        List<RoomParticipantEntity> roomParticipantEntityList=roomParticiptantsEntryRepository.findByParticipantAndStartDate(myMeetingsGetDto.getParticipant(),myMeetingsGetDto.getStartDate());
        if(!(roomParticipantEntityList.isEmpty())){
            ArrayList<MyMeetingsDto> al=new ArrayList<>();
            for(RoomParticipantEntity room:roomParticipantEntityList){
                String str=room.getUniqueHash();
                RoomSlotsEntity roomSlotsEntity=createSlotRepository.findByUniqueHash(str);
                MyMeetingsDto myMeetingsDto=null;

//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//                sdf.setTimeZone(TimeZone.getTimeZone("IST"));
//                Date date = sdf.parse(roomSlotsEntity.getStartTime().toString().trim());
//                roomSlotsEntity.setStartTime(date);
//                Date date2 =sdf.parse(roomSlotsEntity.getEndTime().toString().trim());
//                roomSlotsEntity.setEndTime(date2);

                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                formatter.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata")); // Or whatever IST is supposed to be
                System.out.println(formatter.format(roomSlotsEntity.getStartTime()));


                myMeetingsDto=modelMapper.map(roomSlotsEntity,MyMeetingsDto.class);
               // myMeetingsDto.setStartTime(new Time(roomSlotsEntity.getStartTime().getTime()));
                myMeetingsDto.setRole(room.getRole());
                al.add(myMeetingsDto);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(al);
        }
        else{
            throw new CustomException("No Meetings",HttpStatus.NO_CONTENT);
        }
    }

//    @Override
//    public String findRoomSlots(Date startDateTime,Date endDateTime,String roomName){
//      List<Object> roomSlotsEntities= createSlotRepository.findRoomSlots(startDateTime,endDateTime,roomName);
//     if(roomSlotsEntities.isEmpty()){
//       // throw new CustomException("Slots Available",HttpStatus.ACCEPTED);
//         return "slots available";
//        }
//        else{
//        //throw new CustomException("Slots Occupied",HttpStatus.ACCEPTED);
//         return "slots Occupied";
//     }
//    }

    @Transactional
    @Override
    public ResponseEntity<Object> updateSlots(UpdateDto updateDto) throws CustomException {

        RoomSlotsEntity roomSlotsEntity=createSlotRepository.findByUniqueHash(updateDto.getUniqueHash());
        if(roomSlotsEntity!=null) {
            RoomSlotsEntity roomSlotsEntity1 = modelMapper.map(updateDto, RoomSlotsEntity.class);
            createSlotRepository.save(roomSlotsEntity1);
            roomParticiptantsEntryRepository.removeByUniqueHash(updateDto.getUniqueHash());
            String[] participants = updateDto.getParticipants();
            String uuid = roomSlotsEntity1.getUniqueHash();
            Mapping mapping1 = new Mapping();
            mapping1.setParticipant(updateDto.getRoomHost());
            mapping1.setStartDate(updateDto.getStartTime());
            mapping1.setUniqueHash(uuid);
            mapping1.setRole("manager");
            RoomParticipantEntity roomParticipantEntity1 = modelMapper.map(mapping1, RoomParticipantEntity.class);
            roomParticiptantsEntryRepository.save(roomParticipantEntity1);
            for (String str : participants) {
                RoomParticipantEntity roomParticipantEntity = null;
                Mapping mapping = new Mapping();
                mapping.setParticipant(str);
                mapping.setUniqueHash(uuid);
                mapping.setStartDate(updateDto.getStartTime());
                mapping.setRole("participant");
                roomParticipantEntity = modelMapper.map(mapping, RoomParticipantEntity.class);
                roomParticiptantsEntryRepository.save(roomParticipantEntity);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updated Succesfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Updation failed");
        }

    }
    @Override
    public RoomSlotsEntity getSessionDetails(String uuid) throws CustomException{
        RoomSlotsEntity entity=createSlotRepository.findByUniqueHash(uuid);
        if(entity!=null){
            return entity;
        }
        else{
           throw new CustomException("Session not Available",HttpStatus.NO_CONTENT);
        }
    }


    @Override
    public String findRoomSlots(ValidatingSlotsDto validatingSlotsDto){
        List<Object> roomSlotsEntities= createSlotRepository.findRoomSlots(validatingSlotsDto.getStartDate(),validatingSlotsDto.getEndDate(),validatingSlotsDto.getRoomName());
        if(roomSlotsEntities.isEmpty()){
            // throw new CustomException("Slots Available",HttpStatus.ACCEPTED);
            return "slots available";
        }
        else{
            //throw new CustomException("Slots Occupied",HttpStatus.ACCEPTED);
            return "slots Occupied";
        }
    }


}