package com.accenture.calorie_tracker.domain.bodymass;

import com.accenture.calorie_tracker.core.generic.AbstractEntityService;

public interface BodyMassService extends AbstractEntityService<BodyMass> {
    BodyMass findByValue(float value);
}
