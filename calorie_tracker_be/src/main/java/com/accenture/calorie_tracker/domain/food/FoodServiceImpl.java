package com.accenture.calorie_tracker.domain.food;

import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl extends AbstractEntityServiceImpl<Food> implements FoodService {

    @Autowired
    public FoodServiceImpl(AbstractEntityRepository<Food> repository, Logger logger) {
        super(repository, logger);
    }
}
