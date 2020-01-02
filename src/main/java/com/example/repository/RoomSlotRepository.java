package com.example.repository;

import com.example.Dto.SlotsDto;
import com.example.entity.RoomDetailsEntity;
import com.example.entity.RoomSlotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RoomSlotRepository extends JpaRepository<RoomDetailsEntity,String> {
  //List<RoomSlotEntity> findAllByDateAndRoomName(LocalDate date, String roomName);
   //RoomSlotEntity findByUniqueHash(String uniqueHash);

   @Query("select e.roomName from RoomDetailsEntity e where e.roomFloor= ?1")
   List<String> findAllByFloor(int roomFloor);

   @Query("SELECT DISTINCT e.roomFloor FROM RoomDetailsEntity e")
   List<Integer> findDistinctFloor();


}
