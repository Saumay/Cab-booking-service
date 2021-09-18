package com.example.cabbooking.controller;

import com.example.cabbooking.model.Driver;
import com.example.cabbooking.model.Location;
import com.example.cabbooking.model.Ride;
import com.example.cabbooking.model.Rider;
import com.example.cabbooking.service.CabBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class CabBookingController {
    
    @Autowired
    private CabBookingService cabBookingService;
    
    @RequestMapping("/register/rider")
    public void registerRider(@RequestParam String riderName, @RequestParam double x, @RequestParam double y) {
        cabBookingService.registerRider(riderName, Location.builder().x(x).y(y).build());
    }

    @RequestMapping("/allRiders")
    public Collection<Rider> getAllRiders() {
        return cabBookingService.getAllRiders();
    }

    @RequestMapping("/allDrivers")
    public Collection<Driver> getAllDrivers() {
        return cabBookingService.getAllDrivers();
    }

    @RequestMapping("/allRide")
    public Collection<Ride> getAllRide() {
        return cabBookingService.getAllRides();
    }

    @RequestMapping("/register/driver")
    public void registerDriver(@RequestParam String driverName) {
        cabBookingService.registerDriver(driverName);
    }
    
    @RequestMapping("/update/location/{driverId}")
    public void updateDriverLocation(@PathVariable int driverId, @RequestParam double x, @RequestParam double y) {
        cabBookingService.updateDriverLocation(driverId, Location.builder().x(x).y(y).build());
    }
    
    @RequestMapping("/update/availability/{driverId}")
    public void updateDriverLocation(@PathVariable int driverId, @RequestParam boolean availability) {
        cabBookingService.updateDriverAvailability(driverId, availability);
    }
    
    @RequestMapping("/bookCab/{riderId}")
    public void bookCab(@PathVariable int riderId) {
        cabBookingService.bookCab(riderId);
    }
    
    @RequestMapping("/fetch/history/{riderId}")
    public @ResponseBody List<Ride> fetchRideHistoryForRider(@PathVariable int riderId) {
        return cabBookingService.fetchRideHistoryForRider(riderId);
    }
    
    @RequestMapping("/endTrip/{rideId}")
    public void endTrip(@PathVariable int rideId) {
        cabBookingService.endTrip(rideId);
    }
}
