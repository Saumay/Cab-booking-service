package com.example.cabbooking.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Rider {
    private int id;
    private String name;
    private Location location;
    private List<Integer> rideIds;
}
