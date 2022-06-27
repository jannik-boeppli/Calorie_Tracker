package com.accenture.ch.calorie_tracker.domain.registeredfood;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisteredFoodServiceImpl extends AbstractEntityServiceImpl<RegisteredFood> implements RegisteredFoodService {

    @Autowired
    public RegisteredFoodServiceImpl(AbstractEntityRepository<RegisteredFood> repository, Logger logger) {
        super(repository, logger);
    }
}
