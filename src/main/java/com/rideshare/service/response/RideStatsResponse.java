package com.rideshare.service.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class RideStatsResponse {

    private String name;

    private Integer ridesTaken;

    private Integer ridesOffered;

}
