package com.accenture.ch.calorie_tracker.domain.food;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityController;
import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityService;
import com.accenture.ch.calorie_tracker.core.generic.DTOMapper;
import com.accenture.ch.calorie_tracker.domain.food.dto.FoodDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/food")
public class FoodController extends AbstractEntityController<Food, FoodDTO> {

    public FoodController(AbstractEntityService<Food> service, DTOMapper<Food, FoodDTO> mapper) {
        super(service, mapper);
    }
}
