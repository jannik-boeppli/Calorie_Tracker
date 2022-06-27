package com.accenture.ch.calorie_tracker.domain.usergoal.dto;

import com.accenture.ch.calorie_tracker.core.generic.DTOMapper;
import com.accenture.ch.calorie_tracker.domain.usergoal.UserGoal;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserGoalMapper extends DTOMapper<UserGoal, UserGoalDTO> {
}
