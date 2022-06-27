package com.accenture.ch.calorie_tracker.domain.usergoal;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityController;
import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityService;
import com.accenture.ch.calorie_tracker.core.generic.DTOMapper;
import com.accenture.ch.calorie_tracker.domain.usergoal.dto.UserGoalDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usergoal")
public class UserGoalController extends AbstractEntityController<UserGoal, UserGoalDTO> {

    public UserGoalController(AbstractEntityService<UserGoal> service, DTOMapper<UserGoal, UserGoalDTO> mapper) {
        super(service, mapper);
    }
}
