package com.accenture.ch.calorie_tracker.domain.consumedfood;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntity;
import com.accenture.ch.calorie_tracker.domain.registeredfood.RegisteredFood;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "consumed_food")
public class ConsumedFood extends AbstractEntity {
    @Column(name = "time_of_consumption", nullable = false)
    private LocalDateTime timeOfConsumption;


    @ManyToOne
    @JoinColumn(name = "user_registered_food_id", nullable = false)
    private RegisteredFood registeredFood;

    public ConsumedFood(LocalDateTime timeOfConsumption, RegisteredFood registeredFood) {
        this.timeOfConsumption = timeOfConsumption;
        this.registeredFood = registeredFood;
    }

    public ConsumedFood() {
    }

    @Override
    public ConsumedFood setId(UUID id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getTimeOfConsumption() {
        return timeOfConsumption;
    }

    public ConsumedFood setTimeOfConsumption(LocalDateTime timeOfConsumption) {
        this.timeOfConsumption = timeOfConsumption;
        return this;
    }

    public RegisteredFood getRegisteredFood() {
        return registeredFood;
    }

    public ConsumedFood setRegisteredFood(RegisteredFood registeredFood) {
        this.registeredFood = registeredFood;
        return this;
    }
}
