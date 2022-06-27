package com.accenture.ch.calorie_tracker.domain.user.dto;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityDTO;
import com.accenture.ch.calorie_tracker.domain.bodymass.BodyMass;

public class UserDTO extends AbstractEntityDTO {
    private String username;
    private String firstName;
    private String lastName;
    private int heightInCM;
    private BodyMass bodyMass;

    public String getUsername() {
        return username;
    }

    public UserDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getHeightInCM() {
        return heightInCM;
    }

    public UserDTO setHeightInCM(int heightInCM) {
        this.heightInCM = heightInCM;
        return this;
    }

    public BodyMass getBodyMass() {
        return bodyMass;
    }

    public UserDTO setBodyMass(BodyMass bodyMass) {
        this.bodyMass = bodyMass;
        return this;
    }
}
