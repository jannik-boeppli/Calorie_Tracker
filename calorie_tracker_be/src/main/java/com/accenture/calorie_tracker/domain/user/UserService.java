package com.accenture.calorie_tracker.domain.user;

import com.accenture.calorie_tracker.core.generic.AbstractEntityService;

public interface UserService extends AbstractEntityService<User> {
    User findByUsername(String username);
    User preSave(User entity);
}

