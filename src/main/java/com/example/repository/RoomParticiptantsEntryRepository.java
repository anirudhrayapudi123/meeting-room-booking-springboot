package com.example.repository;

import com.example.entity.RoomParticipantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface RoomParticiptantsEntryRepository extends JpaRepository<RoomParticipantEntity,String> {
    List<RoomParticipantEntity> findByParticipantAndStartDate(String participant,Date startDate);

    void removeByUniqueHash(String uuid);
}
