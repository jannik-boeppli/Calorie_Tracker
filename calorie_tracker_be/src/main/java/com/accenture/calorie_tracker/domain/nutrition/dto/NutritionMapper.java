package com.accenture.calorie_tracker.domain.nutrition.dto;

import com.accenture.calorie_tracker.core.generic.DTOMapper;
import com.accenture.calorie_tracker.domain.nutrition.Nutrition;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NutritionMapper extends DTOMapper<Nutrition, NutritionDTO> {
}
