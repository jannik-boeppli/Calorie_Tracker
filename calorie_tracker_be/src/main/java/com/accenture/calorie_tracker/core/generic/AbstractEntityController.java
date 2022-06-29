package com.accenture.calorie_tracker.core.generic;

import com.accenture.calorie_tracker.core.error.UsernameAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


public abstract class AbstractEntityController<DM extends AbstractEntity, DTO extends AbstractEntityDTO> {
    protected AbstractEntityService<DM> service;
    protected DTOMapper<DM,DTO> mapper;

    public AbstractEntityController(AbstractEntityService<DM> service, DTOMapper<DM, DTO> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<Collection<DTO>> findAll() {
        Collection<DM> dms = service.findAll();

        return new ResponseEntity<>(mapper.toDTOs(dms), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> findById(@PathVariable String id) {
        DM dm = service.findById(id);

        return new ResponseEntity<>(mapper.toDTO(dm), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DTO> create(@RequestBody DTO dto) {
        DM dm = service.create(mapper.fromDTO(dto));

        return new ResponseEntity<>(mapper.toDTO(dm), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DTO> updateById(@PathVariable String id, @RequestBody DTO dto) throws UsernameAlreadyExistsException {
        DM dm = service.updateById(id, mapper.fromDTO(dto));

        return new ResponseEntity<>(mapper.toDTO(dm), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String id) {
        service.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
