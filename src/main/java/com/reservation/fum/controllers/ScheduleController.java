package com.reservation.fum.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.reservation.fum.authorities.BusinessOwner;
import com.reservation.fum.models.Schedule;
import com.reservation.fum.repositories.ScheduleRepository;
import com.reservation.fum.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/business-owner/schedule")
public class ScheduleController {

    private final ScheduleRepository scheduleRepo;
    private final UserRepository userRepo;

    @Autowired
    public ScheduleController(ScheduleRepository schedueRepo,
            UserRepository userRepo) {
        this.scheduleRepo = schedueRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/create")
    public String showCreateScheduleForm(
            Model model, Authentication authentication) {
        BusinessOwner businessOwner = (BusinessOwner) userRepo.findByUsername(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException(authentication.getName()));
        model.addAttribute("businessOwner", businessOwner);
        model.addAttribute("schedule", new Schedule());
        return "create_schedule";
    }

    @PostMapping("/create")
    public String createSchedule(
            @ModelAttribute Schedule schedule, Authentication authentication) {
                return "redirect:/business-owner/dashboard";
    }
}
