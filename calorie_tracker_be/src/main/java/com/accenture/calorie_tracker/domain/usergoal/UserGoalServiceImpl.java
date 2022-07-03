package com.accenture.calorie_tracker.domain.usergoal;


import com.accenture.calorie_tracker.core.error.NotFoundException;
import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import com.accenture.calorie_tracker.domain.goal.Goal;
import com.accenture.calorie_tracker.domain.goal.GoalService;
import com.accenture.calorie_tracker.domain.user.User;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserGoalServiceImpl extends AbstractEntityServiceImpl<UserGoal> implements UserGoalService {

    private GoalService goalService;
    private final UserGoalRepository userGoalRepository;

    @Autowired
    public UserGoalServiceImpl(AbstractEntityRepository<UserGoal> repository, Logger logger,
                               GoalService goalService, UserGoalRepository userGoalRepository) {
        super(repository, logger);
        this.goalService = goalService;
        this.userGoalRepository = userGoalRepository;
    }

    /**
     * This method checks if an entry with the same values already exists to prevent duplicates
     *
     * @param newEntity is the object, that will be saved
     * @return if an entry was found it returns the found entry or else the object from the parameter
     */
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

    /**
     * This method searches for an open goal of the logged in user
     * @return ether the found open goal or null
     */
    @Override
    public UserGoal getOpenGoal() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userGoalRepository.findByUserAndEndTimeIsNull(user);
    }

    /**
     * This method searches for a user goal from the logged in user with the id from the parameter
     * @param id the id to be searched for
     * @return ether the found object or null
     * @throws NotFoundException will be thrown if the object could not be found
     */
    @Override
    public UserGoal findById(String id) throws NotFoundException {
        UserGoal userGoal = super.findById(id);

        return userGoal.getUser().getId()
                .equals(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId())
                ? userGoal : null;
    }

    /**
     * This method returns all user goals from the user
     * @param user the user to be searched for
     * @return a list of all user goals
     */
    @Override
    public List<UserGoal> findAllByUser(User user) {
        return ((UserGoalRepository) repository).findAllByUser(user);
    }
}
