package com.online.metro.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
// on the below line specifying the discriminator value as 
// boy. 
@DiscriminatorValue("ANONYMOUS")
public class Anonymous extends Passenger {

}
