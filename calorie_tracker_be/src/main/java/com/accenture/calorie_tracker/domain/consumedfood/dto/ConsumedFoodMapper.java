package com.accenture.calorie_tracker.domain.consumedfood.dto;

import com.accenture.calorie_tracker.core.generic.DTOMapper;
import com.accenture.calorie_tracker.domain.consumedfood.ConsumedFood;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ConsumedFoodMapper extends DTOMapper<ConsumedFood, ConsumedFoodDTO> {
}
