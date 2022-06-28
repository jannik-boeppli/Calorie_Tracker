package com.accenture.calorie_tracker.domain.usergoal;


import com.accenture.calorie_tracker.core.error.NotFoundException;
import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import com.accenture.calorie_tracker.domain.goal.Goal;
import com.accenture.calorie_tracker.domain.goal.GoalService;
import com.accenture.calorie_tracker.domain.user.User;
import com.accenture.calorie_tracker.domain.user.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserGoalServiceImpl extends AbstractEntityServiceImpl<UserGoal> implements UserGoalService {

    private UserService userService;
    private GoalService goalService;
    private final UserGoalRepository userGoalRepository;

    @Autowired
    public UserGoalServiceImpl(AbstractEntityRepository<UserGoal> repository, Logger logger, UserService userService, GoalService goalService, UserGoalRepository userGoalRepository) {
        super(repository, logger);
        this.userService = userService;
        this.goalService = goalService;
        this.userGoalRepository = userGoalRepository;
    }

    @Override
    protected UserGoal preSave(UserGoal newEntity) {


        //get user
        User user = newEntity.getUser();
        if (user.getId() != null) user = userService.findById(user.getId().toString());
        else if (user.getUsername() != null) user = userService.findByUsername(user.getUsername());
        if (user == null) throw new NotFoundException("User could not be found");
        newEntity.setUser(user);

        // set start_time and end_time
        UserGoal oldGoal = userGoalRepository.findByUserAndEndTimeIsNull(user);
        LocalDateTime now = LocalDateTime.now();
        oldGoal.setEndTime(now);
        updateById(oldGoal.getUser().getId().toString(), oldGoal);
        newEntity.setStartTime(now);

        //get goal
        Goal goal = newEntity.getGoal();
        if(goal.getId() != null) goal = goalService.findById(goal.getId().toString());
        else goal = goalService.findByValue(goal);

        if(goal == null) goal = goalService.save(newEntity.getGoal());
        newEntity.setGoal(goal);

        return newEntity;
    }
}
