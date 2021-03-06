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

    /**
     * This method checks if an entry with the same values already exists to prevent duplicates
     *
     * @param newEntity is the object, that will be saved
     * @return if an entry was found it returns the found entry or else the object from the parameter
     */
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

    /**
     * This method searches for an entry with the same value as the entered object
     *
     * @param food the object to be searched for
     * @return is the object that was found or null
     */
    @Override
    public Food findByValue(Food food) {
        Nutrition nutrition = food.getNutrition();
        if (nutrition.getId() != null) nutrition = nutritionService.findById(nutrition.getId().toString());
        else nutrition = nutritionService.findByValue(food.getNutrition());

        if (nutrition == null) nutrition = nutritionService.save(food.getNutrition());


        return ((FoodRepository) repository).findByNameAndNutrition(
                food.getName(), nutritionService.findByValue(nutrition));
    }
}
