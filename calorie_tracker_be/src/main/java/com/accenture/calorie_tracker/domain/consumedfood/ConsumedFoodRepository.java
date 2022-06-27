package com.accenture.calorie_tracker.domain.consumedfood;

import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ConsumedFoodRepository extends AbstractEntityRepository<ConsumedFood> {
    List<ConsumedFood> findAllByTimeOfConsumption(LocalDateTime date);
}
