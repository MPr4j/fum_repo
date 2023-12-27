package com.reservation.fum.models;

import java.util.List;

import com.reservation.fum.authorities.BusinessOwner;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "services")
public class Service {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "business_owner_id", nullable = false)
    private BusinessOwner businessOwner;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<ReservationRequest> reservationRequests;

    
}
