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

    @GetMapping("/ignore")
    @Override
    public ResponseEntity<UserGoalDTO> findById(String id) {
        return super.findById(id);
    }

    @GetMapping("/open")
    public ResponseEntity<UserGoal> getOpenGoal() {
        return ResponseEntity.ok(userGoalService.getOpenGoal());
    }
}
