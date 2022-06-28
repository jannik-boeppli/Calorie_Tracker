package com.accenture.calorie_tracker.domain.usergoal;

import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.calorie_tracker.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGoalRepository extends AbstractEntityRepository<UserGoal> {
    List<UserGoal> findAllByUser(User user);
}
