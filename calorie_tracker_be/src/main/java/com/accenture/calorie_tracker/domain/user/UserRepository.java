package com.accenture.calorie_tracker.domain.user;

import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractEntityRepository<User> {
    User findByUsername(String username);
    User preSave(User entity);
}
