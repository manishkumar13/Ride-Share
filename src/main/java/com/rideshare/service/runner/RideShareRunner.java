package com.rideshare.service.runner;

import com.rideshare.service.controller.RideShareController;
import com.rideshare.service.entity.User;
import com.rideshare.service.entity.Vehicle;
import com.rideshare.service.enums.Gender;
import com.rideshare.service.enums.RideSelectionStrategy;
import com.rideshare.service.request.OfferRideRequest;
import com.rideshare.service.request.SelectRideRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RideShareRunner implements CommandLineRunner {

    @Autowired
    RideShareController rideShareController;

    @Override
    public void run(String... args) throws Exception {

        // Add users and vehicles
        User rohan = User.builder()
                .name("Rohan")
                .age(36)
                .gender(Gender.Male)
                .build();
        rideShareController.addUser(rohan);
        Vehicle swift = Vehicle.builder()
                .modelName("Swift")
                .userRef("Rohan")
                .registrationNumber("KA-01-12345")
                .build();
        rideShareController.addVehicle(swift);

        User shashank = User.builder()
                .name("Shashank")
                .age(29)
                .gender(Gender.Male)
                .build();
        rideShareController.addUser(shashank);
        Vehicle baleno = Vehicle.builder()
                .modelName("Baleno")
                .userRef("Shashank")
                .registrationNumber("TS-05-62395")
                .build();
        rideShareController.addVehicle(baleno);

        User nandini = User.builder()
                .name("Nandini")
                .age(29)
                .gender(Gender.Female)
                .build();
        rideShareController.addUser(nandini);

        User shipra = User.builder()
                .name("Shipra")
                .age(27)
                .gender(Gender.Male)
                .build();
        rideShareController.addUser(shipra);
        Vehicle polo = Vehicle.builder()
                .modelName("Polo")
                .userRef("Shipra")
                .registrationNumber("KA-05-41491")
                .build();
        rideShareController.addVehicle(polo);
        Vehicle activa = Vehicle.builder()
                .modelName("Activa")
                .userRef("Shipra")
                .registrationNumber("KA-12-12332")
                .build();
        rideShareController.addVehicle(activa);

        User gaurav = User.builder()
                .name("Gaurav")
                .age(29)
                .gender(Gender.Male)
                .build();
        rideShareController.addUser(gaurav);

        User rahul = User.builder()
                .name("Rahul")
                .age(35)
                .gender(Gender.Male)
                .build();
        rideShareController.addUser(rahul);
        Vehicle xuv = Vehicle.builder()
                .modelName("XUV")
                .userRef("Rahul")
                .registrationNumber("KA-05-1234")
                .build();
        rideShareController.addVehicle(xuv);

        // printing all vehicles and users
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Total number of users : " + rideShareController.getUsers().size() + " user list : " + rideShareController.getUsers());
        System.out.println("Total number of vehicles : " + rideShareController.getVehicles().size() + " vehicle list : " + rideShareController.getVehicles());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");

        // offer_rides
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        OfferRideRequest offerRideRequestSwift = new OfferRideRequest(
                "Rohan", "Hyderabad", "Banglore", 1, "Swift", "KA-01-12345"
        );
        System.out.println("Ride offered : " + rideShareController.offerRide(offerRideRequestSwift));
        System.out.println("Total rides offered : " + rideShareController.getRides().size());

        OfferRideRequest offerRideRequestActiva = new OfferRideRequest(
                "Shipra", "Banglore", "Mysore", 1, "Activa", "KA-12-12332"
        );
        System.out.println("Ride offered : " + rideShareController.offerRide(offerRideRequestActiva));
        System.out.println("Total rides offered : " + rideShareController.getRides().size());

        OfferRideRequest offerRideRequestPolo = new OfferRideRequest(
                "Shipra", "Banglore", "Mysore", 2, "Polo", "KA-05-41491"
        );
        System.out.println("Ride offered : " + rideShareController.offerRide(offerRideRequestPolo));
        System.out.println("Total rides offered : " + rideShareController.getRides().size());

        OfferRideRequest offerRideRequestBaleno = new OfferRideRequest(
                "Shashank", "Hyderabad", "Banglore", 2, "Baleno", "TS-05-62395"
        );
        System.out.println("Ride offered : " + rideShareController.offerRide(offerRideRequestBaleno));
        System.out.println("Total rides offered : " + rideShareController.getRides().size());

        OfferRideRequest offerRideRequestXuv = new OfferRideRequest(
                "Rahul", "Hyderabad", "Banglore", 5, "XUV", "KA-05-1234"
        );
        System.out.println("Ride offered : " + rideShareController.offerRide(offerRideRequestXuv));
        System.out.println("Total rides offered : " + rideShareController.getRides().size());

        OfferRideRequest offerRideRequesDuplicate = new OfferRideRequest(
                "Rohan", "Banglore", "Pune", 1, "Swift", "KA-01-12345"
        );
        System.out.println("Ride offered : " + rideShareController.offerRide(offerRideRequesDuplicate));
        System.out.println("Total rides offered : " + rideShareController.getRides().size());

        // select_rides
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        SelectRideRequest selectRideRequestNandini = new SelectRideRequest(
                "Nandini", "Banglore", "Mysore", 1, RideSelectionStrategy.MostVacant, null
        );
        System.out.println(rideShareController.selectRide(selectRideRequestNandini));

        SelectRideRequest selectRideRequestGaurav = new SelectRideRequest(
                "Gaurav", "Banglore", "Mysore", 1, RideSelectionStrategy.PreferredVehicle, "Activa"
        );
        System.out.println(rideShareController.selectRide(selectRideRequestGaurav));

        SelectRideRequest selectRideRequestShashank = new SelectRideRequest(
                "Shashank", "Mumbai", "Banglore", 1, RideSelectionStrategy.MostVacant, null
        );
        System.out.println(rideShareController.selectRide(selectRideRequestShashank));

        SelectRideRequest selectRideRequestRohan = new SelectRideRequest(
                "Rohan", "Hyderabad", "Banglore", 1, RideSelectionStrategy.PreferredVehicle, "Baleno"
        );
        System.out.println(rideShareController.selectRide(selectRideRequestRohan));

        SelectRideRequest selectRideRequestShashank1 = new SelectRideRequest(
                "Shashank", "Hyderabad", "Banglore", 1, RideSelectionStrategy.PreferredVehicle, "Polo"
        );
        System.out.println(rideShareController.selectRide(selectRideRequestShashank1));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");

        // end_rides
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(rideShareController.endRide(offerRideRequestSwift));
        System.out.println(rideShareController.endRide(offerRideRequestActiva));
        System.out.println(rideShareController.endRide(offerRideRequestPolo));
        System.out.println(rideShareController.endRide(offerRideRequestBaleno));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");

        // print_ride_stats
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(rideShareController.totalRidesOfferedAndTaken());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!");
    }
}
