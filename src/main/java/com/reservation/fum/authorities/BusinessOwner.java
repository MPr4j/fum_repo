package com.reservation.fum.authorities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.reservation.fum.models.Schedule;
import com.reservation.fum.models.Service;
import com.reservation.fum.models.ServiceType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "business_owners")
@DiscriminatorValue("BUSINESS_OWNER")
@EqualsAndHashCode(callSuper = false)
public class BusinessOwner extends User {
    private static final long serialVersionUID = 1123213L;
    

    @NotNull
    private ServiceType serviceType;

    @OneToMany(mappedBy = "businessOwner", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Service> services;

    @OneToMany(mappedBy = "businessOwner", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Schedule> schedule;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_BUSINESS_OWNER"));
        return authorities;
    }
}
