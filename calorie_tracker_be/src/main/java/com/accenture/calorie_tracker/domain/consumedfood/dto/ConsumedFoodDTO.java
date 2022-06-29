package com.accenture.calorie_tracker.domain.consumedfood.dto;

import com.accenture.calorie_tracker.core.generic.AbstractEntityDTO;
import com.accenture.calorie_tracker.domain.registeredfood.RegisteredFood;

import java.time.LocalDateTime;

public class ConsumedFoodDTO extends AbstractEntityDTO {
    private LocalDateTime timeOfConsumption;
    private RegisteredFood registeredFood;
    private int amount;

    public LocalDateTime getTimeOfConsumption() {
        return timeOfConsumption;
    }

    public ConsumedFoodDTO setTimeOfConsumption(LocalDateTime timeOfConsumption) {
        this.timeOfConsumption = timeOfConsumption;
        return this;
    }

    public int getAmount() {
        return amount;
    }

    public ConsumedFoodDTO setAmount(int amount) {
        this.amount = amount;
        return this;
    }

    public RegisteredFood getRegisteredFood() {
        return registeredFood;
    }

    public ConsumedFoodDTO setRegisteredFood(RegisteredFood registeredFood) {
        this.registeredFood = registeredFood;
        return this;
    }
}
