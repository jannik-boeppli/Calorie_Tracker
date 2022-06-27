package com.accenture.ch.calorie_tracker.domain.usergoal;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGoalServiceImpl extends AbstractEntityServiceImpl<UserGoal> implements UserGoalService {

    @Autowired
    public UserGoalServiceImpl(AbstractEntityRepository<UserGoal> repository, Logger logger) {
        super(repository, logger);
    }
}
