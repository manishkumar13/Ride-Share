package com.rideshare.service.database;

import com.rideshare.service.entity.Ride;
import com.rideshare.service.entity.User;
import com.rideshare.service.entity.Vehicle;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class RideShareDatabase {
    private List<User> users;
    private List<Vehicle> vehicles;
    private List<Ride> rides;

    RideShareDatabase() {
        users = new ArrayList<>();
        vehicles = new ArrayList<>();
        rides = new ArrayList<>();
    }

}
