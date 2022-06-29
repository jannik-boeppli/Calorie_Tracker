package com.accenture.calorie_tracker.domain.usergoal;

import com.accenture.calorie_tracker.core.generic.AbstractEntityService;
import com.accenture.calorie_tracker.domain.user.User;

import java.util.List;

public interface UserGoalService extends AbstractEntityService<UserGoal> {
    List<UserGoal> findAllByUser(User user);
    UserGoal getOpenGoal();
}
