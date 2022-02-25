package com.rideshare.service.request;

import com.rideshare.service.enums.RideSelectionStrategy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SelectRideRequest {
    private String requestedBy;
    private String source;
    private String destination;
    private Integer requiredSeats;
    private RideSelectionStrategy rideSelectionStrategy;
    private String preferredVehicle;
}
