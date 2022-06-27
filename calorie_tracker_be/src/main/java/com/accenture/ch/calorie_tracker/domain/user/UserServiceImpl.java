package com.accenture.ch.calorie_tracker.domain.user;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import com.accenture.ch.calorie_tracker.domain.bodymass.BodyMassService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends AbstractEntityServiceImpl<User> implements UserService {

    private BodyMassService bodyMassService;

    @Autowired
    public UserServiceImpl(AbstractEntityRepository<User> repository, Logger logger, BodyMassService bodyMassService) {
        super(repository, logger);
        this.bodyMassService = bodyMassService;
    }

    @Override
    protected User preSave(User entity) {
        if (bodyMassService.findByValue(entity.getBodyMass().getWeightInKg()) == null)
            bodyMassService.save(entity.getBodyMass());
        return entity;
    }
}
