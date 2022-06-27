package com.accenture.ch.calorie_tracker.domain.nutrition;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NutritionServiceImpl extends AbstractEntityServiceImpl<Nutrition> implements NutritionService {

    @Autowired
    public NutritionServiceImpl(NutritionRepository repository, Logger logger) {
        super(repository, logger);
    }

    @Override
    public Nutrition findByValue(Nutrition nutrition) {
        return ((NutritionRepository) repository).findByCaloriesAndCarbsAndFatAndProtein(
                Math.min(0, nutrition.getCalories()),
                Math.min(0, nutrition.getCarbs()),
                Math.min(0, nutrition.getFat()),
                Math.min(0, nutrition.getProtein()));
    }
}
