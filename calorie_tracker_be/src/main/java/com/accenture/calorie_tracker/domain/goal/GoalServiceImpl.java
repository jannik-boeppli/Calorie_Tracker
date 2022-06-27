package com.accenture.calorie_tracker.domain.goal;

import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalServiceImpl extends AbstractEntityServiceImpl<Goal> implements GoalService {

    @Autowired
    public GoalServiceImpl(AbstractEntityRepository<Goal> repository, Logger logger) {
        super(repository, logger);
    }
}
