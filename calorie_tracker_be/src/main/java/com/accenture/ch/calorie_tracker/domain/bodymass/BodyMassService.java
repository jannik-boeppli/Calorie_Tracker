package com.accenture.ch.calorie_tracker.domain.bodymass;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityService;

public interface BodyMassService extends AbstractEntityService<BodyMass> {
    BodyMass findByValue(float value);
}
