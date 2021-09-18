package com.example.cabbooking.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Ride {
    private int id;

    private boolean isActive;
    private Location pickupLocation;
    private Location dropLocation;
    private int driverId;
    private int riderId;
}
