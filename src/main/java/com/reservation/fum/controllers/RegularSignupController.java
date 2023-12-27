package com.reservation.fum.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.reservation.fum.authorities.RegularUser;
import com.reservation.fum.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/signup/regular-user")
@SessionAttributes("regularUser")
public class RegularSignupController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    public RegularSignupController(UserRepository userRepo,
            PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String getPage() {
        return "regular_user_signup";
    }

    @ModelAttribute
    public RegularUser regularUser() {
        return new RegularUser();
    }

    @PostMapping
    public String processSignup(
            @Validated @ModelAttribute RegularUser regularUser, Errors errors) {
        if (errors.hasErrors()) {
            log.info("Validation has some errors");
            return "regular_user_signup";
        }
        regularUser.setPassword(passwordEncoder.encode(regularUser.getPassword()));
        userRepo.save(regularUser);
        return "redirect:/login";
    }

}
