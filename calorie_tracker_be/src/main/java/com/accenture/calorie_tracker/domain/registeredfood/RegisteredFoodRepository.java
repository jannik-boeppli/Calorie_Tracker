package com.accenture.calorie_tracker.domain.registeredfood;

import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.calorie_tracker.domain.food.Food;
import com.accenture.calorie_tracker.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisteredFoodRepository extends AbstractEntityRepository<RegisteredFood> {
    RegisteredFood findByFoodAndUser(Food food, User user);

    List<RegisteredFood> findAllByUser(User user);
}
