package com.accenture.ch.calorie_tracker.domain.nutrition;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionRepository extends AbstractEntityRepository<Nutrition> {
}
