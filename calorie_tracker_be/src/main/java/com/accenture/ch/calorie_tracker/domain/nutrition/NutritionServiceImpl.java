package com.accenture.ch.calorie_tracker.domain.nutrition;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutritionServiceImpl extends AbstractEntityServiceImpl<Nutrition> implements NutritionService {

    @Autowired
    public NutritionServiceImpl(AbstractEntityRepository<Nutrition> repository, Logger logger) {
        super(repository, logger);
    }
}
