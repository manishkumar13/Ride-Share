package com.rideshare.service.entity;

import com.rideshare.service.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ride {
    private String offeredBy;
    private String takenBy;
    private String source;
    private String destination;
    private Integer availableSeats;
    private Vehicle vehicle;
    private RideStatus rideStatus;
    private Boolean status;
    private String message;
}
