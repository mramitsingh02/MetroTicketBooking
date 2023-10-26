package com.online.metro.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;

@Entity
// on the below line specifying the discriminator value as
// boy.
@DiscriminatorValue("STAFF")
@AllArgsConstructor
public class Staff extends Passenger {
    private Long passId;
}
