package com.accenture.calorie_tracker.domain.nutrition;

import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionRepository extends AbstractEntityRepository<Nutrition> {
    Nutrition findByCaloriesAndCarbsAndFatAndProtein(int calories, int carbs, int fat, int protein);
}
