package com.accenture.calorie_tracker.domain.usergoal;

import com.accenture.calorie_tracker.core.generic.AbstractEntityController;
import com.accenture.calorie_tracker.core.generic.AbstractEntityService;
import com.accenture.calorie_tracker.core.generic.DTOMapper;
import com.accenture.calorie_tracker.domain.usergoal.dto.UserGoalDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usergoal")
public class UserGoalController extends AbstractEntityController<UserGoal, UserGoalDTO> {
    private final UserGoalService userGoalService;

    public UserGoalController(AbstractEntityService<UserGoal> service, DTOMapper<UserGoal, UserGoalDTO> mapper, UserGoalService userGoalService) {
        super(service, mapper);
        this.userGoalService = userGoalService;
    }

    /**
     * This endpoint will be ignored
     * @param ignore would be the id but cant be set
     * @return the found entry or null
     */
    @GetMapping("/ignore")
    @Override
    public ResponseEntity<UserGoalDTO> findById(String ignore) {
        return super.findById(ignore);
    }

    /**
     * This endpoint tries to find the open goal from the logged in user
     * @return the open goal if found or null
     */
    @GetMapping("/open")
    public ResponseEntity<UserGoal> getOpenGoal() {
        return ResponseEntity.ok(userGoalService.getOpenGoal());
    }
}
