package com.accenture.calorie_tracker.domain.registeredfood.dto;

import com.accenture.calorie_tracker.core.generic.AbstractEntityDTO;
import com.accenture.calorie_tracker.domain.food.Food;
import com.accenture.calorie_tracker.domain.user.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class RegisteredFoodDTO extends AbstractEntityDTO {
    private User user;
    private Food food;

    public User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public RegisteredFoodDTO setUser(User user) {
        this.user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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

