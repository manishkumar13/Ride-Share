package com.rideshare.service.factory;

import com.rideshare.service.enums.RideSelectionStrategy;
import com.rideshare.service.service.MostVacantAlgo;
import com.rideshare.service.service.PreferredVehicleAlgo;
import com.rideshare.service.service.SelectRideAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SelectRideFactory {

    private MostVacantAlgo mostVacantAlgo;
    private PreferredVehicleAlgo preferredVehicleAlgo;

    @Autowired
    public SelectRideFactory(MostVacantAlgo mostVacantAlgo, PreferredVehicleAlgo preferredVehicleAlgo) {
        this.mostVacantAlgo = mostVacantAlgo;
        this.preferredVehicleAlgo = preferredVehicleAlgo;
    }

    public SelectRideAlgorithm getSelectRideAlgorithm(RideSelectionStrategy rideSelectionStrategy) {
        switch (rideSelectionStrategy) {
            case MostVacant:
                return mostVacantAlgo;

            case PreferredVehicle:
                return preferredVehicleAlgo;
        }

        return null;
    }
}
