package com.accenture.ch.calorie_tracker.domain.registeredfood.dto;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityDTO;
import com.accenture.ch.calorie_tracker.domain.food.Food;
import com.accenture.ch.calorie_tracker.domain.user.User;

public class RegisteredFoodDTO extends AbstractEntityDTO {
    private User user;
    private Food food;

    public User getUser() {
        return user;
    }

    public RegisteredFoodDTO setUser(User user) {
        this.user = user;
        return this;
    }

    public Food getFood() {
        return food;
    }

    public RegisteredFoodDTO setFood(Food food) {
        this.food = food;
        return this;
    }
}

