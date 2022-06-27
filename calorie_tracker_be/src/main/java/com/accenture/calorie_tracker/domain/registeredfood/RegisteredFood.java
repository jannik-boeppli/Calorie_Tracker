package com.accenture.calorie_tracker.domain.registeredfood;

import com.accenture.calorie_tracker.core.generic.AbstractEntity;
import com.accenture.calorie_tracker.domain.food.Food;
import com.accenture.calorie_tracker.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "registered_food")
public class RegisteredFood extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_id", nullable = false)
    private Food food;

    public RegisteredFood(User user, Food food) {
        this.user = user;
        this.food = food;
    }

    public RegisteredFood() {
    }

    @Override
    public RegisteredFood setId(UUID id) {
        this.id = id;
        return this;
    }

    public User getUser() {
        return user;
    }

    public RegisteredFood setUser(User user) {
        this.user = user;
        return this;
    }

    public Food getFood() {
        return food;
    }

    public RegisteredFood setFood(Food food) {
        this.food = food;
        return this;
    }
}
