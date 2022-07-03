package com.accenture.calorie_tracker.domain.consumedfood;

import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import com.accenture.calorie_tracker.domain.consumedfood.dto.ConsumedFoodByDateDTO;
import com.accenture.calorie_tracker.domain.nutrition.Nutrition;
import com.accenture.calorie_tracker.domain.registeredfood.RegisteredFood;
import com.accenture.calorie_tracker.domain.registeredfood.RegisteredFoodService;
import com.accenture.calorie_tracker.domain.user.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsumedFoodServiceImpl extends AbstractEntityServiceImpl<ConsumedFood> implements ConsumedFoodService {

    private RegisteredFoodService registeredFoodService;

    @Autowired
    public ConsumedFoodServiceImpl(ConsumedFoodRepository repository, Logger logger,
                                   RegisteredFoodService registeredFoodService) {
        super(repository, logger);
        this.registeredFoodService = registeredFoodService;
    }

    /**
     * This method checks if an entry with the same values already exists to prevent duplicates,
     * also it sets the time of consumption if not already set
     *
     * @param newEntity is the object, that will be saved
     * @return if an entry was found it returns the found entry or else the object from the parameter
     */
    @Override
    protected ConsumedFood preSave(ConsumedFood newEntity) {
        RegisteredFood registeredFood = newEntity.getRegisteredFood();

        if (registeredFood.getId() != null)
            registeredFood = registeredFoodService.findById(registeredFood.getId().toString());
        else registeredFood = registeredFoodService.findByValue(registeredFood);

        if (registeredFood == null) registeredFood = registeredFoodService.save(newEntity.getRegisteredFood());

        newEntity.setRegisteredFood(registeredFood);

        if (newEntity.getTimeOfConsumption() == null) newEntity.setTimeOfConsumption(LocalDateTime.now());
        return newEntity;
    }

    /**
     * This method searches for every consumed food from the current day from the logged in user
     *
     * @return is a collection of the consumed food from the current day from the logged in user
     */
    @Override
    public Collection<ConsumedFood> findAllFromDate() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ConsumedFood> consumedFoodsByDate = repository.findAll().stream().filter(consumedFood ->
                consumedFood.getTimeOfConsumption().getDayOfYear() == LocalDateTime.now().getDayOfYear() &&
                        consumedFood.getTimeOfConsumption().getYear() == LocalDateTime.now().getYear()
        ).collect(Collectors.toList());

        return consumedFoodsByDate.stream()
                .filter(consumedFood -> consumedFood.getRegisteredFood().getUser().getId().equals(currentUser.getId()))
                .collect(Collectors.toList());
    }

    /**
     * This method searches for every consumed food from the current user and summarizes them by the date
     *
     * @return is a collection of summarization from the logged in users consumed food by date
     */
    @Override
    public Collection<ConsumedFoodByDateDTO> findAllFromUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ConsumedFood> consumedFood = repository.findAll();
        consumedFood = consumedFood.stream()
                .filter(consumedFood1 -> consumedFood1.getRegisteredFood().getUser().getId().equals(currentUser.getId()))
                .collect(Collectors.toList());

        List<ConsumedFoodByDateDTO> history = new ArrayList<>();
        for (ConsumedFood food : consumedFood) {
            ConsumedFoodByDateDTO listElement = history.stream().filter(consumedFoodByDateDTO ->
                    consumedFoodByDateDTO.getLocalDateTime().getDayOfYear() == food.getTimeOfConsumption().getDayOfYear() &&
                            consumedFoodByDateDTO.getLocalDateTime().getYear() == food.getTimeOfConsumption().getYear())
                    .findFirst().orElse(null);
            if (listElement == null) {
                listElement = new ConsumedFoodByDateDTO();
                listElement.setLocalDateTime(food.getTimeOfConsumption());
                listElement.setNutrition(new Nutrition(food.getRegisteredFood().getFood().getNutrition()));
                listElement.setAmount(food.getAmount());
                history.add(listElement);
            } else {
                listElement.getNutrition().setProtein(listElement.getNutrition().getProtein() +
                        food.getRegisteredFood().getFood().getNutrition().getProtein());
                listElement.getNutrition().setFat(listElement.getNutrition().getFat() +
                        food.getRegisteredFood().getFood().getNutrition().getFat());
                listElement.getNutrition().setCarbs(listElement.getNutrition().getCarbs() +
                        food.getRegisteredFood().getFood().getNutrition().getCarbs());
                listElement.getNutrition().setCalories(listElement.getNutrition().getCalories() +
                        food.getRegisteredFood().getFood().getNutrition().getCalories());
                listElement.setAmount(listElement.getAmount() + food.getAmount());
            }
        }

        return history;
    }
}
