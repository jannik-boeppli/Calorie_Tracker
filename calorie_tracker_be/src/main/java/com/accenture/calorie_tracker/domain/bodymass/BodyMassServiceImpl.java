package com.accenture.calorie_tracker.domain.bodymass;

import com.accenture.calorie_tracker.core.generic.AbstractEntityRepository;
import com.accenture.calorie_tracker.core.generic.AbstractEntityServiceImpl;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class BodyMassServiceImpl extends AbstractEntityServiceImpl<BodyMass> implements BodyMassService {

    private BodyMassRepository newRepository;

    public BodyMassServiceImpl(AbstractEntityRepository<BodyMass> repository, Logger logger, BodyMassRepository newRepository) {
        super(repository, logger);
        this.newRepository = newRepository;
    }

    /**
     * This method searches for an entry with the same value as the parameter
     *
     * @param value is the weight in kg
     * @return returns a body mass object if an entry was found else null
     */
    @Override
    public BodyMass findByValue(float value) {
        return newRepository.findByWeightInKg(value);
    }

}
