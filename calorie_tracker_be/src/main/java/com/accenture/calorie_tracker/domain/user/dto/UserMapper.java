package com.accenture.calorie_tracker.domain.user.dto;

import com.accenture.calorie_tracker.core.generic.DTOMapper;
import com.accenture.calorie_tracker.domain.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends DTOMapper<User, UserDTO> {
    User fromSignUpDTO(UserSignUpDTO dto);
}
