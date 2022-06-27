package com.accenture.calorie_tracker.domain.goal;

import com.accenture.calorie_tracker.core.generic.AbstractEntityService;

public interface GoalService extends AbstractEntityService<Goal> {
    Goal findByValue(Goal goal);
}
