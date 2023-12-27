package com.reservation.fum.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.reservation.fum.authorities.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
 
    Optional<User> findByUsername(String username);
}
