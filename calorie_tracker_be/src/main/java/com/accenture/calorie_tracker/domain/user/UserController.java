package com.accenture.calorie_tracker.domain.user;

import com.accenture.calorie_tracker.core.generic.AbstractEntityController;
import com.accenture.calorie_tracker.core.generic.AbstractEntityService;
import com.accenture.calorie_tracker.core.generic.DTOMapper;
import com.accenture.calorie_tracker.domain.user.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractEntityController<User, UserDTO> {
    UserService userService;

    public UserController(AbstractEntityService<User> service, DTOMapper<User, UserDTO> mapper, UserService userService) {
        super(service, mapper);
        this.userService = userService;
    }

    @GetMapping("/not-available")
    @Override
    public ResponseEntity<Collection<UserDTO>> findAll() {
        return null;
    }

    @GetMapping("/")
    @Override
    public ResponseEntity<UserDTO> findById(String ignore) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return super.findById(user.getId().toString());
    }

    @PutMapping("/")
    @Override
    public ResponseEntity<UserDTO> updateById(String ignore,@RequestBody UserDTO dto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return super.updateById(user.getId().toString(), dto);
    }

    @Override
    public ResponseEntity<Void> deleteById(String id) {
        return null;
    }
}
