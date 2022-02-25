package com.rideshare.service.dao;

import com.rideshare.service.entity.Ride;
import com.rideshare.service.entity.User;
import com.rideshare.service.entity.Vehicle;
import com.rideshare.service.database.RideShareDatabase;
import com.rideshare.service.enums.RideStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class RideShareDao {

    private RideShareDatabase rideShareDatabase;

    @Autowired
    public RideShareDao(RideShareDatabase rideShareDatabase) {
        this.rideShareDatabase = rideShareDatabase;
    }

    public User findUser(User pUser) {
        return rideShareDatabase.getUsers()
                .stream()
                .filter(user -> user.equals(pUser))
                .findFirst()
                .orElse(null);
    }

    public User addUser(User user) {
        rideShareDatabase.getUsers().add(user);
        return user;
    }

    public Vehicle findVehicle(Vehicle pVehicle) {
        return rideShareDatabase.getVehicles()
                .stream()
                .filter(vehicle -> vehicle.equals(pVehicle))
                .findFirst()
                .orElse(null);
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        rideShareDatabase.getVehicles().add(vehicle);
        return vehicle;
    }

    public Ride findActiveRideForVehicle(String modelName, String registrationNumber) {
        return rideShareDatabase.getRides()
                .stream()
                .filter(ride -> ride.getVehicle().getModelName().equals(modelName)
                        && ride.getVehicle().getRegistrationNumber().equals(registrationNumber)
                        && ride.getRideStatus().equals(RideStatus.AVAILABLE))
                .findFirst()
                .orElse(null);
    }

    public Ride offerRide(Ride ride) {
        rideShareDatabase.getRides().add(ride);
        return ride;
    }

    public List<Ride> fetchAllAvailableRidesForSrcToDest(String source, String destination) {
        return rideShareDatabase.getRides()
                .stream()
                .filter(ride -> ride.getSource().equals(source)
                        && ride.getDestination().equals(destination)
                        && ride.getRideStatus().equals(RideStatus.AVAILABLE))
                .collect(Collectors.toList());
    }

    public Ride endRide(Ride pRide) {
        rideShareDatabase.getRides()
                .forEach(ride -> {
                    if (areRidesSame(pRide, ride)) {
                        ride.setRideStatus(RideStatus.ENDED);
                    }
                });

        return rideShareDatabase.getRides()
                .stream()
                .filter(ride -> areRidesSame(pRide, ride))
                .findFirst()
                .orElse(null);
    }

    private Boolean areRidesSame(Ride ride1, Ride ride2) {
        return ride1.getAvailableSeats().equals(ride2.getAvailableSeats())
                && ride1.getOfferedBy().equals(ride2.getOfferedBy())
                && ride1.getVehicle().equals(ride2.getVehicle())
                && ride1.getSource().equals(ride2.getSource())
                && ride1.getDestination().equals(ride2.getDestination());
    }


    public void markTakenBy(Ride pRide, String requestedBy) {
        rideShareDatabase.getRides()
                .forEach(ride -> {
                    if (areRidesSame(pRide, ride)) {
                        ride.setTakenBy(requestedBy);
                    }
                });
    }

    public List<User> getAllUsers() {
        return rideShareDatabase.getUsers();
    }

    public Integer totalNumberOfRidesOfferedByUser(String userName) {
        return Math.toIntExact(
                rideShareDatabase.getRides().stream().filter(ride -> userName.equals(ride.getOfferedBy())).count()
        );
    }

    public Integer totalNumberOfRidesTakenByUser(String userName) {
        return Math.toIntExact(
                rideShareDatabase.getRides().stream().filter(ride -> userName.equals(ride.getTakenBy())).count()
        );
    }
}
