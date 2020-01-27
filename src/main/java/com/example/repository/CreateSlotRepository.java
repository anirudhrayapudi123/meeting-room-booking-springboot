package com.example.repository;

import com.example.Dto.SlotsDto;
import com.example.entity.RoomSlotEntity;
import com.example.entity.RoomSlotsEntity;
import com.example.service.RoomSlot;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface CreateSlotRepository extends JpaRepository<RoomSlotsEntity,String>{

    RoomSlotsEntity findByUniqueHash(String uniqueHash);
    int removeByUniqueHash(String uniqueHash);

    List<RoomSlotsEntity> findAllByRoomHostAndStartTime(String hostName,Date startTime);



    RoomSlotsEntity findAllByUniqueHashAndRoomName(String uniqueHash, String roomName);


//    @Query(value = "select * from session.session_details where ((room_start_date_time>:startTime AND room_start_date_time<:endTime) OR (room_end_date_time>:startTime AND room_end_date_time<:endTime)) AND room_name=:roomName'"
//            ,nativeQuery = true)
//    List<RoomSlotEntity> findAllBetweenDatesAndRoomName(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("roomName") String roomName);

    // (room_start_date_time > '2020-01-01 06:00:00' AND room_start_date_time < '2020-01-01 07:30:00')
    //or(room_end_date_time  > '2020-01-01 06:00:00' AND room_end_date_time < '2020-01-01 07:30:00')


//    @Query(value="select e from RoomSlotsEntity e where (((e.startTime >=:startTime) \n" +
//            " AND (e.startTime <:endTime)) \n" +
//            " OR ((e.endTime >:startTime) \n" +
//            " AND (e.endTime <=:endTime))) AND e.roomName=:roomName")
//    List<RoomSlotsEntity> findByStartTimeGreaterThanEqualAndStartTimeLessThanOrEndTimeGreaterThanAndEndTimeLessThanEqualAndRoomName(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("roomName") String roomName);

    @Query(value = "select * from session.session_details where \n" +
            " (((room_start_date_time >= :startTime) \n" +
            " AND (room_start_date_time < :endTime)) \n" +
            " OR ((room_end_date_time > :startTime) \n" +
            " AND (room_end_date_time <=  :endTime))) AND (room_name=:roomName);",nativeQuery = true)
    List<Object> findRoomSlots(@Param("startTime") Date startTime,@Param("endTime") Date endTime,@Param("roomName") String roomName);

    @Query(value = "select * from session.session_details where \n" +
            " (((room_start_date_time >= :startTime) \n" +
            " AND (room_start_date_time < :endTime)) \n" +
            " OR ((room_end_date_time > :startTime) \n" +
            " AND (room_end_date_time <=  :endTime))) AND (room_name=:roomName);",nativeQuery = true)
    List<Object> findRoomSlot(@Param("startTime") LocalDateTime startTime,@Param("endTime") LocalDateTime endTime,@Param("roomName") String roomName);
}

