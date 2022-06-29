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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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

    @Transactional
    @Override
    protected UserGoal preSave(UserGoal newEntity) {
        //get user
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newEntity.setUser(user);

        // set start_time and end_time
        UserGoal oldGoal = userGoalRepository.findByUserAndEndTimeIsNull(user);
        LocalDateTime now = LocalDateTime.now();
        if (oldGoal != null) {
            oldGoal.setEndTime(now);
            userGoalRepository.save(oldGoal);
        }
        newEntity.setStartTime(now);

        //get goal
        Goal goal = newEntity.getGoal();
        if (goal.getId() != null) goal = goalService.findById(goal.getId().toString());
        else goal = goalService.findByValue(goal);

        if (goal == null) goal = goalService.save(newEntity.getGoal());
        newEntity.setGoal(goal);

        return newEntity;
    }

    @Override
    public UserGoal getOpenGoal() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userGoalRepository.findByUserAndEndTimeIsNull(user);
    }

    @Override
    public UserGoal findById(String id) throws NotFoundException {
        UserGoal userGoal = super.findById(id);

        return userGoal.getUser().getId()
                .equals(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId())
                ? userGoal : null;
    }

    @Override
    public List<UserGoal> findAllByUser(User user) {
        return ((UserGoalRepository) repository).findAllByUser(user);
    }
}
