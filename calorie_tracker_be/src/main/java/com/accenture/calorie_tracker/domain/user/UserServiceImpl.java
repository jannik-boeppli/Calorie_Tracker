package com.accenture.calorie_tracker.domain.user;

import com.accenture.calorie_tracker.core.error.NotFoundException;
import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import com.accenture.calorie_tracker.domain.bodymass.BodyMassService;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class UserServiceImpl extends AbstractEntityServiceImpl<User> implements UserService, UserDetailsService {

    private BodyMassService bodyMassService;
    private final UserRepository newRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(AbstractEntityRepository<User> repository, Logger logger, BodyMassService bodyMassService, UserRepository newRepository, PasswordEncoder passwordEncoder) {
        super(repository, logger);
        this.bodyMassService = bodyMassService;
        this.newRepository = newRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User preSave(User entity) {
        if (entity.getBodyMass() != null && bodyMassService.findByValue(entity.getBodyMass().getWeightInKg()) == null) {
            entity.setId(UUID.randomUUID());
            bodyMassService.save(entity.getBodyMass());
        }
        return entity;
    }

    @Override
    public User create(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.create(entity);
    }

    @Override
    public User updateById(String id, User entity) throws NotFoundException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user != null) {
            BeanUtils.copyProperties(entity, user, getNullPropertyNames(entity));
            user.setId(UUID.fromString(id));
            entity = save(user);
            return entity;
        } else {
            throw new NotFoundException("User could not be found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = newRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User doesn't exist");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }

    public User findByUsername(String username) {
        return ((UserRepository) repository).findByUsername(username);
    }
}
