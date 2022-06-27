package com.accenture.ch.calorie_tracker.domain.consumedfood;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumedFoodRepository extends AbstractEntityRepository<ConsumedFood> {
}
