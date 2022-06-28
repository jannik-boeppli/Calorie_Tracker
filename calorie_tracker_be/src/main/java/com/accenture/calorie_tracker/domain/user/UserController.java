package com.accenture.calorie_tracker.domain.user;

import com.accenture.calorie_tracker.core.generic.AbstractEntityController;
import com.accenture.calorie_tracker.core.generic.AbstractEntityService;
import com.accenture.calorie_tracker.core.generic.DTOMapper;
import com.accenture.calorie_tracker.domain.user.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractEntityController<User, UserDTO> {

    public UserController(AbstractEntityService<User> service, DTOMapper<User, UserDTO> mapper) {
        super(service, mapper);
    }

    @Override
    public ResponseEntity<Collection<UserDTO>> findAll() {
        return null;
    }

    @Override
    public ResponseEntity<UserDTO> findById(String ignore) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return super.findById(currentUser.getId().toString());
    }

    @Override
    public ResponseEntity<UserDTO> updateById(String ignore, UserDTO dto) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return super.updateById(currentUser.getId().toString(), dto);
    }

    @Override
    public ResponseEntity<Void> deleteById(String id) {
        return null;
    }
}
