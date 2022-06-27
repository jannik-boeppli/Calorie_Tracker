package com.accenture.ch.calorie_tracker.domain.bodymass.dto;

import com.accenture.ch.calorie_tracker.core.generic.DTOMapper;
import com.accenture.ch.calorie_tracker.domain.bodymass.BodyMass;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BodyMassMapper extends DTOMapper<BodyMass, BodyMassDTO> {
}
