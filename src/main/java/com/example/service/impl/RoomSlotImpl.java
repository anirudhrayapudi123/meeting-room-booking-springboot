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

import java.util.ArrayList;
import java.util.List;

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

  //  private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
        List<RoomSlotEntity> slots = slotsRepository.findAllByDate(roomSlotDto.getDate(), roomSlotDto.getRoomName());
        Type listType = new TypeToken<List<SlotsDto>>() {}.getType();
        List<SlotsDto> SlotDTOList = modelMapper.map(slots, listType);
        if(SlotDTOList==null){
            throw new CustomException("Slots Not Available",HttpStatus.NO_CONTENT);
        }
        return SlotDTOList;
    }

    @Override
    public String bookSlots(RoomSlotBookDto roomSlotBookDto){
        List<RoomSlotsEntity> roomSlotEntity = createSlotRepository.findByStartTimeGreaterThanAndStartTimeLessThanOrEndTimeGreaterThanAndEndTimeLessThanAndRoomName(roomSlotBookDto.getStartTime(),roomSlotBookDto.getStartTime(), roomSlotBookDto.getEndTime(), roomSlotBookDto.getEndTime(), roomSlotBookDto.getRoomName());
        if (roomSlotEntity.isEmpty()){

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
            System.out.println("Hello");
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
        else{
           return "slots occupied" ;
        }

    }

    @Transactional
    @Override
    public ResponseEntity<Object> deleteSession(String uniqueHash) throws CustomException{
        RoomSlotsEntity roomSlotsEntity = createSlotRepository.findByUniqueHash(uniqueHash);
        if (roomSlotsEntity == null) {
            throw new CustomException("This Session does not exist", HttpStatus.NO_CONTENT);
        } else if (createSlotRepository.removeByUniqueHash(uniqueHash) != 0) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Session Deleted Succesfully");
        }
        throw new CustomException("This Session does not exist", HttpStatus.NO_CONTENT);
    }


    @Override
    public ResponseEntity<Object> myMeetings(MyMeetingsGetDto myMeetingsGetDto) throws CustomException{
        List<RoomParticipantEntity> roomParticipantEntityList=roomParticiptantsEntryRepository.findByParticipantAndStartDate(myMeetingsGetDto.getParticipant(),myMeetingsGetDto.getStartDate());
        if(!(roomParticipantEntityList.isEmpty())){
            ArrayList<MyMeetingsDto> al=new ArrayList<>();
            for(RoomParticipantEntity room:roomParticipantEntityList){
                String str=room.getUniqueHash();
                RoomSlotsEntity roomSlotsEntity=createSlotRepository.findByUniqueHash(str);
                MyMeetingsDto myMeetingsDto=null;

                myMeetingsDto=modelMapper.map(roomSlotsEntity,MyMeetingsDto.class);

                myMeetingsDto.setRole(room.getRole());
                al.add(myMeetingsDto);
            }
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(al);
        }
        else{
            throw new CustomException("No Meetings",HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public String getValidation(ValidatingSlotsDto validatingSlotsDto){
      List<RoomSlotsEntity> roomSlotsEntities= createSlotRepository.findByStartTimeGreaterThanAndStartTimeLessThanOrEndTimeGreaterThanAndEndTimeLessThanAndRoomName(validatingSlotsDto.getStartDate(),validatingSlotsDto.getStartDate(),validatingSlotsDto.getEndDate(),validatingSlotsDto.getEndDate(),validatingSlotsDto.getRoomName());
     if(roomSlotsEntities.isEmpty()){
       // throw new CustomException("Slots Available",HttpStatus.ACCEPTED);
         return "slots available";
        }

        else{
        //throw new CustomException("Slots Occupied",HttpStatus.ACCEPTED);
         return "slots Occupied";
     }

    }
    @Override
    public ResponseEntity<Object> updateSlots(UpdateDto updateDto) throws CustomException {
            RoomSlotsEntity roomSlotsEntity1 = modelMapper.map(updateDto, RoomSlotsEntity.class);
            createSlotRepository.save(roomSlotsEntity1);
           throw new CustomException("Updated Succesfully",HttpStatus.ACCEPTED);
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

}