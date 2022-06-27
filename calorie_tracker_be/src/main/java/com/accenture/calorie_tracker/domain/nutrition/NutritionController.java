package com.accenture.calorie_tracker.domain.nutrition;

import com.accenture.calorie_tracker.core.generic.AbstractEntityController;
import com.accenture.calorie_tracker.core.generic.AbstractEntityService;
import com.accenture.calorie_tracker.core.generic.DTOMapper;
import com.accenture.calorie_tracker.domain.nutrition.dto.NutritionDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/nutrition")
public class NutritionController extends AbstractEntityController<Nutrition, NutritionDTO> {

    public NutritionController(AbstractEntityService<Nutrition> service, DTOMapper<Nutrition, NutritionDTO> mapper) {
        super(service, mapper);
    }
}
