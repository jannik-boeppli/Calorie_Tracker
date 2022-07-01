package com.accenture.calorie_tracker.domain.user;

import com.accenture.calorie_tracker.core.generic.AbstractEntity;
import com.accenture.calorie_tracker.domain.bodymass.BodyMass;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User extends AbstractEntity {
    @Column(unique = true, name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "height_in_cm")
    private int heightInCM;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "body_mass")
    private BodyMass bodyMass;

    public User(String username, String password, String firstName, String lastName, int heightInCM, BodyMass bodyMass) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.heightInCM = heightInCM;
        this.bodyMass = bodyMass;
    }

    public User() {
    }

    @Override
    public User setId(UUID id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", heightInCM=" + heightInCM +
                ", bodyMass=" + bodyMass +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public int getHeightInCM() {
        return heightInCM;
    }

    public User setHeightInCM(int heightInCM) {
        this.heightInCM = heightInCM;

        return this;
    }

    public BodyMass getBodyMass() {
        return bodyMass;
    }

    public User setBodyMass(BodyMass bodyMass) {
        this.bodyMass = bodyMass;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}
