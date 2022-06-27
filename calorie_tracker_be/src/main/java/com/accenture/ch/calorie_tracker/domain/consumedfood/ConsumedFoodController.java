package com.accenture.ch.calorie_tracker.domain.consumedfood;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityController;
import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityService;
import com.accenture.ch.calorie_tracker.core.generic.DTOMapper;
import com.accenture.ch.calorie_tracker.domain.consumedfood.dto.ConsumedFoodDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumedfood")
public class ConsumedFoodController extends AbstractEntityController<ConsumedFood, ConsumedFoodDTO> {

    public ConsumedFoodController(AbstractEntityService<ConsumedFood> service, DTOMapper<ConsumedFood, ConsumedFoodDTO> mapper) {
        super(service, mapper);
    }
}
