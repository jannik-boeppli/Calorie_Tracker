package com.accenture.ch.calorie_tracker.domain.nutrition;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityService;

public interface NutritionService extends AbstractEntityService<Nutrition> {
    Nutrition findByValue(Nutrition nutrition);
}
