package com.rideshare.service.entity;

import com.rideshare.service.enums.Gender;
import lombok.*;

import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private Gender gender;
    private Integer age;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name) && Objects.equals(gender, user.gender) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender.name(), age);
    }
}
