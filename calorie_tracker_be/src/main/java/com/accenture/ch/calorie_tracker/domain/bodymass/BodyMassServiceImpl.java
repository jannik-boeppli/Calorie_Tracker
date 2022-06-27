package com.accenture.ch.calorie_tracker.domain.bodymass;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class BodyMassServiceImpl extends AbstractEntityServiceImpl<BodyMass> implements BodyMassService {

    private BodyMassRepository newRepository;

    public BodyMassServiceImpl(AbstractEntityRepository<BodyMass> repository, Logger logger, BodyMassRepository newRepository) {
        super(repository, logger);
        this.newRepository = newRepository;
    }


    @Override
    public BodyMass findByValue(float value) {
        return newRepository.findByWeightInKg(value);
    }

}
