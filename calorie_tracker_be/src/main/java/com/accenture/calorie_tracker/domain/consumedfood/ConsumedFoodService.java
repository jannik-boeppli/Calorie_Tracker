package com.accenture.calorie_tracker.domain.consumedfood;

import com.accenture.calorie_tracker.core.generic.AbstractEntityService;
import com.accenture.calorie_tracker.domain.consumedfood.dto.ConsumedFoodByDateDTO;

import java.util.Collection;

public interface ConsumedFoodService extends AbstractEntityService<ConsumedFood> {

    Collection<ConsumedFood> findAllFromDate();

    Collection<ConsumedFoodByDateDTO> findAllFromUser();
}
