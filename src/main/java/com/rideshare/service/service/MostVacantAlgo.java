package com.rideshare.service.service;

import com.rideshare.service.entity.Ride;
import com.rideshare.service.dao.RideShareDao;
import com.rideshare.service.request.SelectRideRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MostVacantAlgo implements SelectRideAlgorithm{

    private RideShareDao rideShareDao;

    @Autowired
    public MostVacantAlgo(RideShareDao rideShareDao) {
        this.rideShareDao = rideShareDao;
    }

    @Override
    public List<Ride> findRides(SelectRideRequest selectRideRequest) {
        List<Ride> ridesWithGivenSrcDest = rideShareDao.fetchAllAvailableRidesForSrcToDest(selectRideRequest.getSource(),
                                                                                        selectRideRequest.getDestination());

        Integer maxSeats = ridesWithGivenSrcDest.stream()
                                                .filter(ride -> ride.getAvailableSeats() >= selectRideRequest.getRequiredSeats())
                                                .mapToInt(Ride::getAvailableSeats)
                                                .max()
                                                .orElse(-1);


        return ridesWithGivenSrcDest.stream()
                                    .filter(ride -> Objects.equals(ride.getAvailableSeats(), maxSeats))
                                    .collect(Collectors.toList());
    }
}
