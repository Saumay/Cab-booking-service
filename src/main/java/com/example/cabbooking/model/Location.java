package com.example.cabbooking.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Location {
    private double x;
    private double y;

    public double getDistance(Location location2) {
        return Math.sqrt(Math.pow(this.x - location2.getX(), 2) + Math.pow(this.y - location2.getY(), 2));
    }
}
