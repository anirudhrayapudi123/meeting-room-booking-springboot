package com.example.repository;

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
//    @Query(value = "select * from session.session_details where ((room_start_date_time>:startTime AND room_start_date_time<:endTime) OR (room_end_date_time>:startTime AND room_end_date_time<:endTime)) AND room_name=:roomName'"
//            ,nativeQuery = true)
//    List<RoomSlotEntity> findAllBetweenDatesAndRoomName(@Param("startTime") String startTime, @Param("endTime") String endTime, @Param("roomName") String roomName);

    // (room_start_date_time > '2020-01-01 06:00:00' AND room_start_date_time < '2020-01-01 07:30:00')
    //or(room_end_date_time  > '2020-01-01 06:00:00' AND room_end_date_time < '2020-01-01 07:30:00')
    List<RoomSlotsEntity> findByStartTimeGreaterThanAndStartTimeLessThanOrEndTimeGreaterThanAndEndTimeLessThanAndRoomName(Date startTime1,Date startTime2,Date endTime1,Date endTime2,String roomName);

}

