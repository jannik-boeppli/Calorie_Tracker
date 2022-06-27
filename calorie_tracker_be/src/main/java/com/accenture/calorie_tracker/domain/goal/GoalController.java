package com.accenture.calorie_tracker.domain.goal;

import com.accenture.calorie_tracker.core.generic.AbstractEntityController;
import com.accenture.calorie_tracker.core.generic.AbstractEntityService;
import com.accenture.calorie_tracker.core.generic.DTOMapper;
import com.accenture.calorie_tracker.domain.goal.dto.GoalDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goal")
public class GoalController extends AbstractEntityController<Goal, GoalDTO> {

    public GoalController(AbstractEntityService<Goal> service, DTOMapper<Goal, GoalDTO> mapper) {
        super(service, mapper);
    }
}
