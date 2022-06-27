package com.accenture.calorie_tracker.domain.goal;

import com.accenture.calorie_tracker.core.generic.AbstractEntity;
import com.accenture.calorie_tracker.domain.bodymass.BodyMass;
import com.accenture.calorie_tracker.domain.nutrition.Nutrition;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "goal")
public class Goal extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "nutrition_id")
    private Nutrition nutrition;

    @ManyToOne
    @JoinColumn(name = "body_mass_id")
    private BodyMass bodyMass;

    public Goal(Nutrition nutrition, BodyMass bodyMass) {
        this.nutrition = nutrition;
        this.bodyMass = bodyMass;
    }

    public Goal() {
    }

    @Override
    public Goal setId(UUID id) {
        this.id = id;
        return this;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public Goal setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
        return this;
    }

    public BodyMass getBodyMass() {
        return bodyMass;
    }

    public Goal setBodyMass(BodyMass bodyMass) {
        this.bodyMass = bodyMass;
        return this;
    }
}
