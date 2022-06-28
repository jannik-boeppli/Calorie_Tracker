package com.accenture.calorie_tracker.domain.consumedfood;

import com.accenture.calorie_tracker.core.generic.AbstractEntityController;
import com.accenture.calorie_tracker.domain.consumedfood.dto.ConsumedFoodByDateDTO;
import com.accenture.calorie_tracker.domain.consumedfood.dto.ConsumedFoodDTO;
import com.accenture.calorie_tracker.domain.consumedfood.dto.ConsumedFoodMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/consumedfood")
public class ConsumedFoodController extends AbstractEntityController<ConsumedFood, ConsumedFoodDTO> {

    public ConsumedFoodController(ConsumedFoodService service, ConsumedFoodMapper mapper) {
        super(service, mapper);
    }

    @GetMapping("/now")
    public ResponseEntity<Collection<ConsumedFoodDTO>> findAllNow() {

        Collection<ConsumedFood> dms = ((ConsumedFoodService) service).findAllFromDate();

        return new ResponseEntity<>(mapper.toDTOs(dms), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Collection<ConsumedFoodByDateDTO>> findAllFromUser() {
        Collection<ConsumedFoodByDateDTO> dms = ((ConsumedFoodService) service).findAllFromUser();

        return new ResponseEntity<>(dms, HttpStatus.OK);
    }


}
