package com.accenture.ch.calorie_tracker.domain.registeredfood;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityController;
import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityService;
import com.accenture.ch.calorie_tracker.core.generic.DTOMapper;
import com.accenture.ch.calorie_tracker.domain.registeredfood.dto.RegisteredFoodDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registeredfood")
public class RegisteredFoodController extends AbstractEntityController<RegisteredFood, RegisteredFoodDTO> {

    public RegisteredFoodController(AbstractEntityService<RegisteredFood> service, DTOMapper<RegisteredFood, RegisteredFoodDTO> mapper) {
        super(service, mapper);
    }
}
