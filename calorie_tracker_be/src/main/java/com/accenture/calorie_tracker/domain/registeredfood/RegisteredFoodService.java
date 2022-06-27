package com.accenture.calorie_tracker.domain.registeredfood;

import com.accenture.calorie_tracker.core.generic.AbstractEntityService;

public interface RegisteredFoodService extends AbstractEntityService<RegisteredFood> {
    RegisteredFood findByValue(RegisteredFood registeredFood);
}
