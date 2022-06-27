package com.accenture.ch.calorie_tracker.domain.bodymass;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BodyMassRepository extends AbstractEntityRepository<BodyMass> {
    BodyMass findByWeightInKg(float weightInKg);
}
