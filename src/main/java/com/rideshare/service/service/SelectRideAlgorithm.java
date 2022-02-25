package com.rideshare.service.service;

import com.rideshare.service.entity.Ride;
import com.rideshare.service.request.SelectRideRequest;

import java.util.List;

public interface SelectRideAlgorithm {
    List<Ride> findRides(SelectRideRequest selectRideRequest);
}
