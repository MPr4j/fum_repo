package com.reservation.fum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.reservation.fum.authorities.BusinessOwner;
import com.reservation.fum.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class BusinessOwnerService {
    private final UserRepository userRepository;

    @Autowired
    public BusinessOwnerService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public BusinessOwner getAuthenticatedBusinessOwner() {
        // Retrieve the authenticated user's username
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Retrieve the BusinessOwner by username
        return userRepository.findByUsername(username)
                .filter(user -> user instanceof BusinessOwner)
                .map(user -> (BusinessOwner) user)
                .orElseThrow(() -> new RuntimeException("Authenticated user is not a BusinessOwner"));
    }
}
