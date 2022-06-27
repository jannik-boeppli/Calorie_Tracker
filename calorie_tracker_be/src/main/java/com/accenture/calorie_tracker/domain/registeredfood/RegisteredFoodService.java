package com.accenture.calorie_tracker.domain.registeredfood;

import com.accenture.calorie_tracker.core.generic.AbstractEntityService;
import com.accenture.calorie_tracker.domain.user.User;

import java.util.List;

public interface RegisteredFoodService extends AbstractEntityService<RegisteredFood> {
    RegisteredFood findByValue(RegisteredFood registeredFood);

    List<RegisteredFood> findAllByUser(User user);
}
