package com.accenture.calorie_tracker.domain.food;

import com.accenture.calorie_tracker.core.generic.AbstractEntityService;

public interface FoodService extends AbstractEntityService<Food> {
    Food findByValue(Food food);
}
