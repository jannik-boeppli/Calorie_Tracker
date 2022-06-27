package com.accenture.calorie_tracker.domain.consumedfood;

import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import com.accenture.calorie_tracker.domain.registeredfood.RegisteredFood;
import com.accenture.calorie_tracker.domain.registeredfood.RegisteredFoodService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumedFoodServiceImpl extends AbstractEntityServiceImpl<ConsumedFood> implements ConsumedFoodService {

    private RegisteredFoodService registeredFoodService;

    @Autowired
    public ConsumedFoodServiceImpl(ConsumedFoodRepository repository, Logger logger,
                                   RegisteredFoodService registeredFoodService) {
        super(repository, logger);
        this.registeredFoodService = registeredFoodService;
    }

    @Override
    protected ConsumedFood preSave(ConsumedFood newEntity) {
        RegisteredFood registeredFood = newEntity.getRegisteredFood();

        if(registeredFood.getId() != null)
            registeredFood = registeredFoodService.findById(registeredFood.getId().toString());
        else registeredFood = registeredFoodService.findByValue(registeredFood);

        if(registeredFood == null) registeredFood = registeredFoodService.save(newEntity.getRegisteredFood());

        newEntity.setRegisteredFood(registeredFood);

        return newEntity;
    }
}
