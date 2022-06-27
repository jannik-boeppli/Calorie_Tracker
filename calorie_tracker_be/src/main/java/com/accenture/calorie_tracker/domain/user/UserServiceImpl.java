package com.accenture.calorie_tracker.domain.user;

import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import com.accenture.calorie_tracker.domain.bodymass.BodyMassService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl extends AbstractEntityServiceImpl<User> implements UserService, UserDetailsService {

    private BodyMassService bodyMassService;
    private final UserRepository newRepository;

    @Autowired
    public UserServiceImpl(AbstractEntityRepository<User> repository, Logger logger, BodyMassService bodyMassService, UserRepository newRepository) {
        super(repository, logger);
        this.bodyMassService = bodyMassService;
        this.newRepository = newRepository;
    }

    @Override
    protected User preSave(User entity) {
        if (bodyMassService.findByValue(entity.getBodyMass().getWeightInKg()) == null)
            bodyMassService.save(entity.getBodyMass());
        return entity;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = newRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User doesn't exist");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), "placeholder change later", new ArrayList<>());
    }
}
