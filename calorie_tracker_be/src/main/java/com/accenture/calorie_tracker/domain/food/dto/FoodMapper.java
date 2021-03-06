package com.accenture.calorie_tracker.domain.food.dto;

import com.accenture.calorie_tracker.core.generic.DTOMapper;
import com.accenture.calorie_tracker.domain.food.Food;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FoodMapper extends DTOMapper<Food, FoodDTO> {
}
