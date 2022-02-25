package com.rideshare.service.controller;

import com.rideshare.service.entity.Ride;
import com.rideshare.service.entity.User;
import com.rideshare.service.entity.Vehicle;
import com.rideshare.service.database.RideShareDatabase;
import com.rideshare.service.request.OfferRideRequest;
import com.rideshare.service.request.SelectRideRequest;
import com.rideshare.service.response.RideStatsResponse;
import com.rideshare.service.service.RideShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ride-share")
public class RideShareController {

    private RideShareService rideShareService;
    private RideShareDatabase rideShareDatabase;

    @Autowired
    public RideShareController(RideShareService rideShareService, RideShareDatabase rideShareDatabase) {
        this.rideShareService = rideShareService;
        this.rideShareDatabase = rideShareDatabase;
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        try {
            return rideShareService.addUser(user);
        } catch (Exception e) {
            throw new RuntimeException("User Already Exists");
        }
    }

    @PostMapping("/vehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) throws RuntimeException {
        return rideShareService.addVehicle(vehicle);
    }

    @PostMapping("/offer-ride")
    public Ride offerRide(@RequestBody OfferRideRequest offerRideRequest) throws RuntimeException {
        return rideShareService.offerRide(offerRideRequest);
    }

    @PostMapping("/select-ride")
    public List<Ride> selectRide(@RequestBody SelectRideRequest selectRideRequest) {
        return rideShareService.selectRide(selectRideRequest);
    }

    @PutMapping("/end-ride")
    public Ride endRide(@RequestBody OfferRideRequest offerRideRequest) {
        return rideShareService.endRide(offerRideRequest);
    }

    @GetMapping("/print-ride-stats")
    public List<RideStatsResponse> totalRidesOfferedAndTaken() {
        return rideShareService.totalRidesOfferedAndTaken();
    }

    @GetMapping("/all-users")
    public List<User> getUsers() {
        return rideShareDatabase.getUsers();
    }

    @GetMapping("/all-vehicles")
    public List<Vehicle> getVehicles() {
        return rideShareDatabase.getVehicles();
    }

    @GetMapping("/all-rides")
    public List<Ride> getRides() {
        return rideShareDatabase.getRides();
    }

}
