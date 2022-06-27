package com.accenture.calorie_tracker.domain.food;


import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import com.accenture.calorie_tracker.domain.nutrition.Nutrition;
import com.accenture.calorie_tracker.domain.nutrition.NutritionService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl extends AbstractEntityServiceImpl<Food> implements FoodService {

    private NutritionService nutritionService;

    @Autowired
    public FoodServiceImpl(FoodRepository repository, Logger logger, NutritionService nutritionService) {
        super(repository, logger);
        this.nutritionService = nutritionService;
    }

    @Override
    protected Food preSave(Food newEntity) {
        Nutrition nutrition = null;
        if (newEntity.getNutrition() == null) newEntity.setNutrition(new Nutrition());

        if (newEntity.getNutrition().getId() != null)
            nutrition = nutritionService.findById(newEntity.getNutrition().getId().toString());
        else nutrition = nutritionService.findByValue(newEntity.getNutrition());

        if (nutrition == null) nutrition = nutritionService.save(newEntity.getNutrition());

        newEntity.setNutrition(nutrition);

        return newEntity;
    }

    @Override
    public Food findByValue(Food food) {
        return ((FoodRepository) repository).findByNameAndNutrition(
                food.getName(), nutritionService.findByValue(food.getNutrition()));
    }
}
