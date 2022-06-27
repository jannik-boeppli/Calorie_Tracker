package com.accenture.calorie_tracker.domain.registeredfood;

import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredFoodRepository extends AbstractEntityRepository<RegisteredFood> {
}
