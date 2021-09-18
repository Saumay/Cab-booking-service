package com.example.cabbooking.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Driver {
    private int id;

    private String name;
    private boolean isAvailable;
    private Location location;
}
