package com.accenture.ch.calorie_tracker.domain.usergoal.dto;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityDTO;
import com.accenture.ch.calorie_tracker.domain.goal.Goal;
import com.accenture.ch.calorie_tracker.domain.user.User;

import java.time.LocalDateTime;

public class UserGoalDTO extends AbstractEntityDTO {
    private Goal goal;
    private User user;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Goal getGoal() {
        return goal;
    }

    public UserGoalDTO setGoal(Goal goal) {
        this.goal = goal;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserGoalDTO setUser(User user) {
        this.user = user;
        return this;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public UserGoalDTO setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public UserGoalDTO setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }
}
