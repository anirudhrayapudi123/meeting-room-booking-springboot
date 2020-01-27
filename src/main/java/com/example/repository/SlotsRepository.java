package com.example.repository;

import com.example.Dto.MyMeetingsDto;
import com.example.Dto.SlotsDto;
import com.example.entity.RoomSlotEntity;
import com.example.entity.RoomSlotsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SlotsRepository extends JpaRepository<RoomSlotEntity,String> {

    @Query(value = "select * from session.session_details s where ':username' in s.room_participants",nativeQuery = true)
    List<RoomSlotEntity> findAllByParticipants(@Param("username") String participant);

    //@Query(value = "select * from session.session_details " +
      //      "where room_name=:?2 and room_start_date_time::text like ': ?1%'",nativeQuery = true)

    //@Query(value = " select e from RoomSlotEntity e where FUNCTION(DATE,e.startTime) LIKE :date AND e.roomName=:roomname")

//    @Query(value = "select * from session.session_details where CAST(room_start_date_time AS text) like %:time% AND room_name=:roomname ",nativeQuery = true)
//    List<RoomSlotEntity> findAllByDate(@Param("time") String startTime,@Param("roomname") String roomName);


//    @Query(value = "select * from session.session_details where like %:time% AND room_name=:roomname ",nativeQuery = true)
//    List<RoomSlotEntity> findAllByStartTimeAndRoomName(@Param("time") Date startTime, @Param("roomname") String roomName);

   // @Query(value = "select * from session.session_details  where room_name=:?1",nativeQuery = true)

  //  select e from RoomSlotEntity e where CAST(e.startTime AS text) like '%:time%'
   // List<RoomSlotEntity> findByRoomName(String roomName);

   // select * from session.session_details u where u.room_name like %:roomname%

    // List<RoomSlotEntity> findAllByStartDateLessThanEqualAndEndDateGreaterThanEqual(OffsetDateTime endDate, OffsetDateTime startDate);


    // select * from session.session_details where room_name='omega' and room_start_date_time::text like '2020-01-01%'
  // select e.roomName from RoomSlotEntity e where e.startTime::text like :date% AND e.roomName=:roomname

}
