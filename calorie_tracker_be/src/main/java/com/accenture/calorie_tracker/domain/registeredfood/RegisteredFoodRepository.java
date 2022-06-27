package com.accenture.calorie_tracker.domain.registeredfood;

import com.accenture.calorie_tracker.domain.food.Food;
import com.accenture.calorie_tracker.domain.user.User;
import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredFoodRepository extends AbstractEntityRepository<RegisteredFood> {
    RegisteredFood findByFoodAndUser(Food food, User user);

}
