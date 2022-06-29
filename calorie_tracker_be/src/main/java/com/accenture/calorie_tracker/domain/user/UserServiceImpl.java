package com.accenture.calorie_tracker.domain.user;

import com.accenture.calorie_tracker.core.error.NotFoundException;
import com.accenture.calorie_tracker.core.error.UsernameAlreadyExistsException;
import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import com.accenture.calorie_tracker.domain.bodymass.BodyMass;
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
        if (entity.getBodyMass() != null) {
            BodyMass foundBodyMass = bodyMassService.findByValue(entity.getBodyMass().getWeightInKg());
            if (foundBodyMass == null) {
                entity.setBodyMass(bodyMassService.save(entity.getBodyMass()));
            } else {
                entity.getBodyMass().setId(foundBodyMass.getId());
            }
        }
        return entity;
    }

    @Override
    public User create(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return super.create(entity);
    }

    @Override
    public User updateById(String id, User entity) throws NotFoundException, UsernameAlreadyExistsException {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user != null) {
            entity.setId(user.getId());
            User foundUser = findByUsername(entity.getUsername());
            if (foundUser != null && !foundUser.getId().equals(user.getId()))
                throw new UsernameAlreadyExistsException();
            BeanUtils.copyProperties(entity, user, getNullPropertyNames(entity));
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
