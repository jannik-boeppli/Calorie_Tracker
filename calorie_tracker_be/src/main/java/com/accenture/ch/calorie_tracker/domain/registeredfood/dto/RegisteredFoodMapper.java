package com.accenture.ch.calorie_tracker.domain.registeredfood.dto;

import com.accenture.ch.calorie_tracker.core.generic.DTOMapper;
import com.accenture.ch.calorie_tracker.domain.registeredfood.RegisteredFood;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RegisteredFoodMapper extends DTOMapper<RegisteredFood, RegisteredFoodDTO> {
}
