package com.accenture.ch.calorie_tracker.domain.bodymass.dto;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityDTO;

public class BodyMassDTO extends AbstractEntityDTO {
    private float weightInKg;

    public float getWeightInKg() {
        return weightInKg;
    }

    public BodyMassDTO setWeightInKg(float weightInKg) {
        this.weightInKg = weightInKg;
        return this;
    }
}
