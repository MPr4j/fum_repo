package com.reservation.fum.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.reservation.fum.authorities.BusinessOwner;
import com.reservation.fum.authorities.User;
import com.reservation.fum.models.Service;
import com.reservation.fum.models.ServiceType;
import com.reservation.fum.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/signup/business-owner")
@SessionAttributes("businessOwner")
public class BusinessSignUpController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public BusinessSignUpController(UserRepository userRepo,
    PasswordEncoder passwordEncoder
    ){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping()
    public String getSignupPage(){
        return "business_signup";
    }

    @ModelAttribute
    public void addServiceTypes(Model model){
        model.addAttribute("serviceType", ServiceType.values());
    }

    @ModelAttribute(name = "businessOwner")
    public BusinessOwner getUser(){
        return new BusinessOwner();
    }
    
    @PostMapping
    public String signup(
       @Validated @ModelAttribute BusinessOwner businessOwner,
        Errors errors){
        if (errors.hasErrors()){
            log.info("validation has some errors" + errors.getAllErrors().get(0) + businessOwner.getEmail());
            return "business_signup";
        }
        businessOwner.setPassword(passwordEncoder.encode(businessOwner.getPassword()));
        userRepo.save(businessOwner);
        return "redirect:/login";
    }
}
