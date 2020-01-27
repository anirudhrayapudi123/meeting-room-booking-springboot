package com.example.repository;

import com.example.Dto.Mapping;
import com.example.entity.RoomParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Repository
public interface RoomParticiptantsEntryRepository extends JpaRepository<RoomParticipantEntity,String> {
    List<RoomParticipantEntity> findByParticipantAndStartDate(String participant,Date startDate);

    List<RoomParticipantEntity> findByStartDate(Date startDate);


    int removeByUniqueHash(String uuid);
}
