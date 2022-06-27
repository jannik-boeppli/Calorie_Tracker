package com.accenture.ch.calorie_tracker.domain.food.dto;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityDTO;
import com.accenture.ch.calorie_tracker.domain.nutrition.Nutrition;

public class FoodDTO extends AbstractEntityDTO {
    private String name;
    private Nutrition nutrition;

    public String getName() {
        return name;
    }

    public FoodDTO setName(String name) {
        this.name = name;
        return this;
    }

    public Nutrition getNutrition() {
        return nutrition;
    }

    public FoodDTO setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
        return this;
    }
}
