package com.accenture.calorie_tracker.domain.registeredfood;

import com.accenture.calorie_tracker.core.error.NotFoundException;
import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import com.accenture.calorie_tracker.domain.food.Food;
import com.accenture.calorie_tracker.domain.food.FoodService;
import com.accenture.calorie_tracker.domain.user.User;
import org.slf4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegisteredFoodServiceImpl extends AbstractEntityServiceImpl<RegisteredFood> implements RegisteredFoodService {

    private FoodService foodService;

    public RegisteredFoodServiceImpl(RegisteredFoodRepository repository, Logger logger, FoodService foodService) {
        super(repository, logger);
        this.foodService = foodService;
    }

    /**
     * This method checks if an entry with the same values already exists to prevent duplicates
     *
     * @param newEntity is the object, that will be saved
     * @return if an entry was found it returns the found entry or else the object from the parameter
     */
    @Override
    protected RegisteredFood preSave(RegisteredFood newEntity) {
        newEntity.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        //get food
        Food food = newEntity.getFood();
        if (food.getId() != null) food = foodService.findById(food.getId().toString());
        else food = foodService.findByValue(newEntity.getFood());

        if (food == null) food = foodService.save(newEntity.getFood());
        newEntity.setFood(food);

        RegisteredFood existingFood = findByValue(newEntity);
        return existingFood != null ? existingFood : newEntity;
    }

    /**
     * This method searches for all entries from the logged in user
     *
     * @return a list of the registered food from the current user
     */
    @Override
    public List<RegisteredFood> findAll() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return super.findAll().stream()
                .filter(registeredFood ->
                        registeredFood.getUser().getId().equals(currentUser.getId())).collect(Collectors.toList());
    }

    /**
     * This method searches for a registered food with a specific id from the logged in user
     *
     * @param id to be searched for
     * @return a registered food from the logged in user with the id from the parameter
     * @throws NotFoundException if the object could not be found
     */
    @Override
    public RegisteredFood findById(String id) throws NotFoundException {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        RegisteredFood registeredFood = super.findById(id);

        return registeredFood.getUser().getId().equals(currentUser.getId()) ? registeredFood : null;
    }

    /**
     * This method tries to find a registered food by its values
     *
     * @param registeredFood is the object to be searched for
     * @return is ether the found entry or null
     */
    @Override
    public RegisteredFood findByValue(RegisteredFood registeredFood) {
        return ((RegisteredFoodRepository) repository).findByFoodAndUser(registeredFood.getFood(), registeredFood.getUser());
    }

    /**
     * This method tries to find all entries from a user
     *
     * @param user is the user to be searched for
     * @return is a list of all the registered food from a user
     */
    @Override
    public List<RegisteredFood> findAllByUser(User user) {
        return ((RegisteredFoodRepository) repository).findAllByUser(user);
    }
}
