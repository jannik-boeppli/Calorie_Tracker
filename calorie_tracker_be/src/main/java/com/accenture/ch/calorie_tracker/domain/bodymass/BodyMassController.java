package com.accenture.ch.calorie_tracker.domain.bodymass;

import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityController;
import com.accenture.ch.calorie_tracker.core.generic.AbstractEntityService;
import com.accenture.ch.calorie_tracker.core.generic.DTOMapper;
import com.accenture.ch.calorie_tracker.domain.bodymass.dto.BodyMassDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bodymass")
public class BodyMassController extends AbstractEntityController<BodyMass, BodyMassDTO> {

    public BodyMassController(AbstractEntityService<BodyMass> service, DTOMapper<BodyMass, BodyMassDTO> mapper) {
        super(service, mapper);
    }
}
