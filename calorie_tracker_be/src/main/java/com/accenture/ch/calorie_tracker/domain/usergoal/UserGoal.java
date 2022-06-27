package com.accenture.ch.calorie_tracker.domain.usergoal;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntity;
import com.accenture.ch.calorie_tracker.domain.goal.Goal;
import com.accenture.ch.calorie_tracker.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_goal")
public class UserGoal extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "goal_id", nullable = false)
    private Goal goal;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    public UserGoal(Goal goal, User user, LocalDateTime startTime, LocalDateTime endTime) {
        this.goal = goal;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public UserGoal() {
    }

    @Override
    public UserGoal setId(UUID id) {
        this.id = id;
        return this;
    }

    public Goal getGoal() {
        return goal;
    }

    public UserGoal setGoal(Goal goal) {
        this.goal = goal;
        return this;
    }

    public User getUser() {
        return user;
    }

    public UserGoal setUser(User user) {
        this.user = user;
        return this;

    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public UserGoal setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public UserGoal setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
        return this;
    }
}
