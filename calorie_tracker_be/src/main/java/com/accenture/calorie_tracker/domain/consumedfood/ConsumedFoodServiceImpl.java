package com.accenture.calorie_tracker.domain.consumedfood;

import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumedFoodServiceImpl extends AbstractEntityServiceImpl<ConsumedFood> implements ConsumedFoodService {

    @Autowired
    public ConsumedFoodServiceImpl(AbstractEntityRepository<ConsumedFood> repository, Logger logger) {
        super(repository, logger);
    }
}
