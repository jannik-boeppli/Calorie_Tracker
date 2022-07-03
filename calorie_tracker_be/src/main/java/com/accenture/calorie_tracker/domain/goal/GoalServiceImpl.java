package com.accenture.calorie_tracker.domain.goal;


import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import com.accenture.calorie_tracker.domain.bodymass.BodyMass;
import com.accenture.calorie_tracker.domain.bodymass.BodyMassService;
import com.accenture.calorie_tracker.domain.nutrition.Nutrition;
import com.accenture.calorie_tracker.domain.nutrition.NutritionService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalServiceImpl extends AbstractEntityServiceImpl<Goal> implements GoalService {

    private NutritionService nutritionService;
    private BodyMassService bodyMassService;

    @Autowired
    public GoalServiceImpl(GoalRepository repository, Logger logger,
                           NutritionService nutritionService, BodyMassService bodyMassService) {
        super(repository, logger);
        this.nutritionService = nutritionService;
        this.bodyMassService = bodyMassService;
    }

    /**
     * This method checks if an entry with the same values already exists to prevent duplicates
     *
     * @param newEntity is the object, that will be saved
     * @return if an entry was found it returns the found entry or else the object from the parameter
     */
    @Override
    protected Goal preSave(Goal newEntity) {
        if (newEntity.getNutrition() != null) {
            Nutrition nutrition = newEntity.getNutrition();
            if (nutrition.getId() != null) nutrition = nutritionService.findById(nutrition.getId().toString());
            else nutrition = nutritionService.findByValue(nutrition);

            if (nutrition == null) nutrition = nutritionService.save(newEntity.getNutrition());
            newEntity.setNutrition(nutrition);
        }

        if (newEntity.getBodyMass() != null) {
            BodyMass bodyMass = newEntity.getBodyMass();
            if (bodyMass.getId() != null) bodyMass = bodyMassService.findById(bodyMass.getId().toString());
            else bodyMass = bodyMassService.findByValue(bodyMass.getWeightInKg());

            if (bodyMass == null) bodyMass = bodyMassService.save(newEntity.getBodyMass());
            newEntity.setBodyMass(bodyMass);
        }
        return newEntity;
    }

    /**
     * This method tries to find an entry with the same values
     *
     * @param goal the object to be searched for
     * @return is the found entry or null
     */
    @Override
    public Goal findByValue(Goal goal) {
        BodyMass bodyMass = goal.getBodyMass();
        if (bodyMass != null) {
            if (bodyMass.getId() != null) bodyMass = bodyMassService.findById(bodyMass.getId().toString());
            else bodyMass = bodyMassService.findByValue(goal.getBodyMass().getWeightInKg());

            if (bodyMass == null) bodyMass = bodyMassService.save(goal.getBodyMass());
        }

        Nutrition nutrition = goal.getNutrition();
        if (nutrition != null) {
            if (nutrition.getId() != null) nutrition = nutritionService.findById(nutrition.getId().toString());
            else nutrition = nutritionService.findByValue(goal.getNutrition());

            if (nutrition == null) nutrition = nutritionService.save(goal.getNutrition());
        }

        return ((GoalRepository) repository).findByBodyMassAndNutrition(bodyMass, nutrition);
    }
}
