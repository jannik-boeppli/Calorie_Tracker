package com.accenture.calorie_tracker.domain.consumedfood.dto;

import com.accenture.calorie_tracker.core.generic.AbstractEntityDTO;
import com.accenture.calorie_tracker.domain.nutrition.Nutrition;

import java.time.LocalDateTime;

public class ConsumedFoodByDateDTO extends AbstractEntityDTO {
    private Nutrition nutrition;
    private LocalDateTime localDateTime;
    private int amount;

    public Nutrition getNutrition() {
        return nutrition;
    }

    public ConsumedFoodByDateDTO setNutrition(Nutrition nutrition) {
        this.nutrition = nutrition;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public ConsumedFoodByDateDTO setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public ConsumedFoodByDateDTO setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }
}
