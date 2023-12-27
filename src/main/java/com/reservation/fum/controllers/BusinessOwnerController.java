package com.reservation.fum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.reservation.fum.authorities.BusinessOwner;
import com.reservation.fum.models.Service;
import com.reservation.fum.services.BusinessOwnerService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/business-owner")
public class BusinessOwnerController {

    private final BusinessOwnerService businessOwnerService;

    @Autowired
    public BusinessOwnerController(BusinessOwnerService businessOwnerService) {
        this.businessOwnerService = businessOwnerService;
    }

    @GetMapping("/dashboard")
    @Transactional
    public String showDashboard(Model model) {
        // Retrieve the authenticated BusinessOwner user
        BusinessOwner businessOwner = businessOwnerService.getAuthenticatedBusinessOwner();

        // Retrieve services for the business owner
        List<Service> services = businessOwner.getServices();

        model.addAttribute("services", services);
        return "business_owner_dashboard";
    }
}
