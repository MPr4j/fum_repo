package com.reservation.fum.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.reservation.fum.authorities.BusinessOwner;
import com.reservation.fum.models.Schedule;

@Repository
public interface ScheduleRepository extends CrudRepository<Schedule,Long>  {
    List<Schedule> findByBusinessOwner(BusinessOwner businessOwner);
}
