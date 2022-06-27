package com.accenture.ch.calorie_tracker.domain.consumedfood.dto;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityDTO;
import com.accenture.ch.calorie_tracker.domain.registeredfood.RegisteredFood;

import java.time.LocalDateTime;

public class ConsumedFoodDTO extends AbstractEntityDTO {
    private LocalDateTime timeOfConsumption;
    private RegisteredFood registeredFood;

    public LocalDateTime getTimeOfConsumption() {
        return timeOfConsumption;
    }

    public ConsumedFoodDTO setTimeOfConsumption(LocalDateTime timeOfConsumption) {
        this.timeOfConsumption = timeOfConsumption;
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
