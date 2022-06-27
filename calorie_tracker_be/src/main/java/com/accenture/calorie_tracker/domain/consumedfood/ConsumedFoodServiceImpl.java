package com.accenture.calorie_tracker.domain.consumedfood;

import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import com.accenture.calorie_tracker.domain.consumedfood.dto.ConsumedFoodByDateDTO;
import com.accenture.calorie_tracker.domain.registeredfood.RegisteredFood;
import com.accenture.calorie_tracker.domain.registeredfood.RegisteredFoodService;
import com.accenture.calorie_tracker.domain.user.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

        if (registeredFood.getId() != null)
            registeredFood = registeredFoodService.findById(registeredFood.getId().toString());
        else registeredFood = registeredFoodService.findByValue(registeredFood);

        if (registeredFood == null) registeredFood = registeredFoodService.save(newEntity.getRegisteredFood());

        newEntity.setRegisteredFood(registeredFood);

        return newEntity;
    }

    @Override
    public Collection<ConsumedFood> findAllFromDate() {
        List<ConsumedFood> consumedFoodsByDate =
                ((ConsumedFoodRepository) repository).findAllByTimeOfConsumption(LocalDateTime.now());
        List<RegisteredFood> registeredFoods = registeredFoodService
                .findAllByUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        return consumedFoodsByDate.stream().filter(consumedFood -> registeredFoods.stream().filter(registeredFood ->
                consumedFood.getRegisteredFood().getId().equals(registeredFood.getId())).toList().size() > 0).toList();
    }

    @Override
    public Collection<ConsumedFoodByDateDTO> findAllFromUser() {
        List<ConsumedFood> consumedFoodsByDate = repository.findAll();
        List<RegisteredFood> registeredFoods = registeredFoodService
                .findAllByUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        //list of every consumed food from one person
        consumedFoodsByDate = consumedFoodsByDate.stream().filter(consumedFood -> registeredFoods.stream().filter(registeredFood ->
                consumedFood.getRegisteredFood().getId().equals(registeredFood.getId())).toList().size() > 0).toList();

        //split the list by date
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        List<List<ConsumedFood>> consumedFoodSplitByDate = new ArrayList<>();
        for (ConsumedFood consumedFoodEntry : consumedFoodsByDate) {
            boolean newDate = true;
            for (List<ConsumedFood> foodList : consumedFoodSplitByDate) {
                if (fmt.format(foodList.get(0).getTimeOfConsumption()).equals(fmt.format(consumedFoodEntry.getTimeOfConsumption()))) {
                    foodList.add(consumedFoodEntry);
                    newDate = false;
                    break;
                }
            }
            if (newDate) {
                List<ConsumedFood> newList = new ArrayList<>();
                newList.add(consumedFoodEntry);
                consumedFoodSplitByDate.add(newList);
            }
        }

        List<ConsumedFoodByDateDTO> history = new ArrayList<>();
        for (List<ConsumedFood> foodList : consumedFoodSplitByDate) {
            //set default data
            ConsumedFoodByDateDTO list = new ConsumedFoodByDateDTO();
            list.getNutrition().setCalories(0);
            list.getNutrition().setProtein(0);
            list.getNutrition().setFat(0);
            list.getNutrition().setCarbs(0);
            list.setLocalDateTime(foodList.get(0).getTimeOfConsumption());
            //combine all values into one object
            for (ConsumedFood element:foodList) {
                list.getNutrition().setCalories(list.getNutrition().getCalories() +
                        element.getRegisteredFood().getFood().getNutrition().getCalories());
                list.getNutrition().setFat(list.getNutrition().getFat() +
                        element.getRegisteredFood().getFood().getNutrition().getFat());
                list.getNutrition().setCarbs(list.getNutrition().getCarbs() +
                        element.getRegisteredFood().getFood().getNutrition().getCarbs());
                list.getNutrition().setProtein(list.getNutrition().getProtein() +
                        element.getRegisteredFood().getFood().getNutrition().getProtein());
            }
            history.add(list);
        }

        return history;
    }
}
