package com.reservation.fum.authorities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "regular_users")
@DiscriminatorValue("REGULAR_USER")
public class RegularUser extends User {

}
