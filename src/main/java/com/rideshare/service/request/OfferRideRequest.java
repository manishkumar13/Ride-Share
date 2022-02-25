package com.rideshare.service.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OfferRideRequest {
    private String offeredBy;
    private String source;
    private String destination;
    private Integer availableSeats;
    private String modelName;
    private String registrationNumber;
}
