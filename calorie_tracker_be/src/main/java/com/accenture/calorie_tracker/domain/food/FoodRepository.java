package com.accenture.calorie_tracker.domain.food;

import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.calorie_tracker.domain.nutrition.Nutrition;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends AbstractEntityRepository<Food> {
    Food findByNameAndNutrition(String name, Nutrition nutrition);
}
