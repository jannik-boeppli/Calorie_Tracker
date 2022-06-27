package com.accenture.calorie_tracker.domain.goal.dto;

import com.accenture.calorie_tracker.core.generic.AbstractEntityDTO;
import com.accenture.calorie_tracker.domain.bodymass.BodyMass;
import com.accenture.calorie_tracker.domain.nutrition.Nutrition;

public class GoalDTO extends AbstractEntityDTO {
    private Nutrition nutrition;
    private BodyMass bodyMass;

    public Nutrition getNutrition() {
        return nutrition;
    }

    public GoalDTO setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
        return this;
    }

    public BodyMass getBodyMass() {
        return bodyMass;
    }

    public GoalDTO setBodyMass(BodyMass bodyMass) {
        this.bodyMass = bodyMass;
        return this;
    }
}
