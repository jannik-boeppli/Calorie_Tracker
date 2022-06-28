package com.accenture.calorie_tracker.domain.registeredfood;

import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import com.accenture.calorie_tracker.domain.food.Food;
import com.accenture.calorie_tracker.domain.food.FoodService;
import com.accenture.calorie_tracker.domain.user.User;
import com.accenture.calorie_tracker.domain.user.UserService;
import org.slf4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredFoodServiceImpl extends AbstractEntityServiceImpl<RegisteredFood> implements RegisteredFoodService {

    private UserService userService;
    private FoodService foodService;

    public RegisteredFoodServiceImpl(RegisteredFoodRepository repository, Logger logger, UserService userService, FoodService foodService) {
        super(repository, logger);
        this.userService = userService;
        this.foodService = foodService;
    }

    @Override
    protected RegisteredFood preSave(RegisteredFood newEntity) {
        newEntity.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        //get food
        Food food = newEntity.getFood();
        if (food.getId() != null) food = foodService.findById(food.getId().toString());
        else food = foodService.findByValue(newEntity.getFood());

        if (food == null) food = foodService.save(newEntity.getFood());

        newEntity.setFood(food);

        return newEntity;
    }

    @Override
    public RegisteredFood findByValue(RegisteredFood registeredFood) {
        return ((RegisteredFoodRepository) repository).findByFoodAndUser(registeredFood.getFood(), registeredFood.getUser());
    }

    @Override
    public List<RegisteredFood> findAllByUser(User user) {
        return ((RegisteredFoodRepository) repository).findAllByUser(user);
    }
}
