package com.accenture.ch.calorie_tracker.domain.bodymass;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "body_mass")
public class BodyMass extends AbstractEntity {
    @Column(name = "weight_in_kg", nullable = false, unique = true)
    private float weightInKg;

    public BodyMass(float weightInKg) {
        this.weightInKg = weightInKg;
    }

    public BodyMass() {
    }

    @Override
    public BodyMass setId(UUID id) {
        this.id = id;
        return this;
    }

    public float getWeightInKg() {
        return weightInKg;
    }

    public BodyMass setWeightInKg(float weightInKg) {
        this.weightInKg = weightInKg;
        return this;
    }
}
