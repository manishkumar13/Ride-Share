package com.rideshare.service.service;

import com.rideshare.service.entity.Ride;
import com.rideshare.service.entity.User;
import com.rideshare.service.entity.Vehicle;
import com.rideshare.service.dao.RideShareDao;
import com.rideshare.service.enums.RideStatus;
import com.rideshare.service.factory.SelectRideFactory;
import com.rideshare.service.request.OfferRideRequest;
import com.rideshare.service.request.SelectRideRequest;
import com.rideshare.service.response.RideStatsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideShareService {

    private RideShareDao rideShareDao;
    private SelectRideFactory selectRideFactory;

    @Autowired
    public RideShareService(RideShareDao rideShareDao, SelectRideFactory selectRideFactory) {
        this.rideShareDao = rideShareDao;
        this.selectRideFactory = selectRideFactory;
    }

    public User addUser(User user) {
        if (rideShareDao.findUser(user) != null) {
            throw new RuntimeException("User Already Exists");
        }

        return rideShareDao.addUser(user);
    }

    public Vehicle addVehicle(Vehicle vehicle) {
        if (rideShareDao.findVehicle(vehicle) != null) {
            throw new RuntimeException("Vehicle Already Exists");
        }

        return rideShareDao.addVehicle(vehicle);
    }


    public Ride offerRide(OfferRideRequest offerRideRequest) {
        if (rideShareDao.findActiveRideForVehicle(offerRideRequest.getModelName(),
                offerRideRequest.getRegistrationNumber()) != null) {
            return Ride.builder()
                    .status(false)
                    .message("Vehicle Not Available for a New Ride")
                    .offeredBy(offerRideRequest.getOfferedBy())
                    .availableSeats(offerRideRequest.getAvailableSeats())
                    .rideStatus(RideStatus.NOT_AVAILABLE)
                    .source(offerRideRequest.getSource())
                    .destination(offerRideRequest.getDestination())
                    .vehicle(Vehicle.builder()
                            .userRef(offerRideRequest.getOfferedBy())
                            .modelName(offerRideRequest.getModelName())
                            .registrationNumber(offerRideRequest.getRegistrationNumber())
                            .build())
                    .build();
        }

        return rideShareDao.offerRide(Ride.builder()
                .offeredBy(offerRideRequest.getOfferedBy())
                .availableSeats(offerRideRequest.getAvailableSeats())
                .rideStatus(RideStatus.AVAILABLE)
                .source(offerRideRequest.getSource())
                .destination(offerRideRequest.getDestination())
                .message("Ride successfully offered")
                .status(true)
                .vehicle(Vehicle.builder()
                        .userRef(offerRideRequest.getOfferedBy())
                        .modelName(offerRideRequest.getModelName())
                        .registrationNumber(offerRideRequest.getRegistrationNumber())
                        .build())
                .build());
    }

    public List<Ride> selectRide(SelectRideRequest selectRideRequest) {
        List<Ride> rideList = selectRideFactory.getSelectRideAlgorithm(selectRideRequest.getRideSelectionStrategy())
                .findRides(selectRideRequest);
        if (!rideList.isEmpty()) {
            rideShareDao.markTakenBy(rideList.get(0), selectRideRequest.getRequestedBy());
        }
        return rideList;
    }

    public Ride endRide(OfferRideRequest offerRideRequest) {
        return rideShareDao.endRide(Ride.builder()
                .offeredBy(offerRideRequest.getOfferedBy())
                .availableSeats(offerRideRequest.getAvailableSeats())
                .rideStatus(RideStatus.AVAILABLE)
                .source(offerRideRequest.getSource())
                .destination(offerRideRequest.getDestination())
                .vehicle(Vehicle.builder()
                        .userRef(offerRideRequest.getOfferedBy())
                        .modelName(offerRideRequest.getModelName())
                        .registrationNumber(offerRideRequest.getRegistrationNumber())
                        .build())
                .build());
    }

    public List<RideStatsResponse> totalRidesOfferedAndTaken() {
        List<User> userList = rideShareDao.getAllUsers();
        return userList.stream()
                .map(user -> RideStatsResponse.builder()
                        .name(user.getName())
                        .ridesOffered(rideShareDao.totalNumberOfRidesOfferedByUser(user.getName()))
                        .ridesTaken(rideShareDao.totalNumberOfRidesTakenByUser(user.getName()))
                        .build()
                )
                .collect(Collectors.toList());
    }
}
