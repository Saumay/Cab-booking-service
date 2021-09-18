package com.example.cabbooking.service;

import com.example.cabbooking.model.Driver;
import com.example.cabbooking.model.Location;
import com.example.cabbooking.model.Ride;
import com.example.cabbooking.model.Rider;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class CabBookingService {

    private AtomicInteger riderId = new AtomicInteger(0);
    private AtomicInteger driverId = new AtomicInteger(0);
    private AtomicInteger rideId = new AtomicInteger(0);

    private Map<Integer, Rider> riderMap = new HashMap<>();
    private Map<Integer, Driver> driverMap = new HashMap<>();
    private Map<Integer, Ride> rideMap = new HashMap<>();

    public void registerRider(String name, Location location) {
        int id = riderId.getAndIncrement();
        Rider rider = Rider.builder()
                .id(id)
                .location(location)
                .name(name)
                .rideIds(new ArrayList<>()).build();
        riderMap.put(id, rider);
    }

    public void registerDriver(String name) {
        int id = driverId.getAndIncrement();
        Driver driver = Driver.builder()
                .id(id)
                .name(name).build();
        driverMap.put(id, driver);
    }

    public void updateDriverLocation(int driverId, Location location) {
        if(driverMap.containsKey(driverId))
            driverMap.get(driverId).setLocation(location);
    }

    public void updateDriverAvailability(int driverId, boolean isAvailable) {
        if(driverMap.containsKey(driverId))
            driverMap.get(driverId).setAvailable(isAvailable);
    }

    public void bookCab(int riderId) {
        Rider rider = riderMap.get(riderId);
        Location riderLocation = rider.getLocation();

        double minDistance = Double.MAX_VALUE;
        int closestDriverId = 0;
        for(Driver driver : driverMap.values()) {
            if(riderLocation.getDistance(driver.getLocation()) < minDistance) {
                minDistance = riderLocation.getDistance(driver.getLocation());
                closestDriverId = driver.getId();
            }
        }
        int id = rideId.getAndIncrement();
        Ride ride = Ride.builder()
                .id(rideId.getAndIncrement())
                .driverId(closestDriverId)
                .riderId(riderId)
                .isActive(true).build();
        driverMap.get(closestDriverId).setAvailable(false);
        rider.getRideIds().add(id);
        rideMap.put(id, ride);
    }

    public List<Ride> fetchRideHistoryForRider(int riderId) {
        return riderMap.get(riderId).getRideIds().stream()
                .map(id -> rideMap.get(id))
                .collect(Collectors.toList());
    }

    public void endTrip(int rideId) {
        rideMap.get(rideId).setActive(false);
    }

    public Collection<Rider> getAllRiders() {
        return riderMap.values();
    }

    public Collection<Driver> getAllDrivers() {
        return driverMap.values();
    }

    public Collection<Ride> getAllRides() {
        return rideMap.values();
    }
}
