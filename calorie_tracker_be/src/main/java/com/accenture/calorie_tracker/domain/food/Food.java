package com.accenture.calorie_tracker.domain.food;

import com.accenture.calorie_tracker.core.generic.AbstractEntity;
import com.accenture.calorie_tracker.domain.nutrition.Nutrition;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "food")
public class Food extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "nutrition_id", nullable = false)
    private Nutrition nutrition;

    public Food(String name, Nutrition nutrition) {
        this.name = name;
        this.nutrition = nutrition;
    }

    public Food() {
    }

    @Override
    public Food setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Food setName(String name) {
        this.name = name;
        return this;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public Food setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
        return this;
    }
}
