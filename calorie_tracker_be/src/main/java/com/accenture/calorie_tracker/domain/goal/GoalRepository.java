package com.accenture.calorie_tracker.domain.goal;

import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.calorie_tracker.domain.bodymass.BodyMass;
import com.accenture.calorie_tracker.domain.nutrition.Nutrition;
import org.springframework.stereotype.Repository;

@Repository
public interface GoalRepository extends AbstractEntityRepository<Goal> {
    Goal findByBodyMassAndNutrition(BodyMass bodyMass, Nutrition nutrition);
}
