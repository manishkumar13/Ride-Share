package com.rideshare.service.service;

import com.rideshare.service.entity.Ride;
import com.rideshare.service.dao.RideShareDao;
import com.rideshare.service.request.SelectRideRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreferredVehicleAlgo implements SelectRideAlgorithm{

    private RideShareDao rideShareDao;

    @Autowired
    public PreferredVehicleAlgo(RideShareDao rideShareDao) {
        this.rideShareDao = rideShareDao;
    }

    @Override
    public List<Ride> findRides(SelectRideRequest selectRideRequest) {
        List<Ride> ridesWithGivenSrcDest = rideShareDao.fetchAllAvailableRidesForSrcToDest(selectRideRequest.getSource(),
                                                                                    selectRideRequest.getDestination());

        return ridesWithGivenSrcDest.stream()
                                    .filter(ride -> ride.getVehicle().getModelName()
                                                        .equals(selectRideRequest.getPreferredVehicle()))
                                    .collect(Collectors.toList());
    }
}
