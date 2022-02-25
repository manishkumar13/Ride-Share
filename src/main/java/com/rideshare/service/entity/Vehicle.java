package com.rideshare.service.entity;

import lombok.*;

import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
    private String userRef;
    private String modelName;
    private String registrationNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(userRef, vehicle.userRef) && Objects.equals(modelName, vehicle.modelName)
                && Objects.equals(registrationNumber, vehicle.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userRef, modelName, registrationNumber);
    }
}
