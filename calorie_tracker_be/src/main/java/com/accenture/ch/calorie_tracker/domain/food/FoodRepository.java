package com.accenture.ch.calorie_tracker.domain.food;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends AbstractEntityRepository<Food> {
}
