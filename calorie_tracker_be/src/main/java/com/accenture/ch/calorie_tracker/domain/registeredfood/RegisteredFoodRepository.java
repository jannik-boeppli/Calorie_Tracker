package com.accenture.ch.calorie_tracker.domain.registeredfood;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.ch.calorie_tracker.domain.food.Food;
import com.accenture.ch.calorie_tracker.domain.user.User;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredFoodRepository extends AbstractEntityRepository<RegisteredFood> {
    RegisteredFood findByFoodAndUser(Food food, User user);
}
