package com.nalepka.controller;

import com.nalepka.model.Selector;
import com.nalepka.model.Unit;
import com.nalepka.service.impl.UnitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/unit")
public class UnitController {
    @Autowired
    UnitServiceImpl unitService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Unit> getAll(){
        return unitService.getAll();
    }

    @RequestMapping(value = "/{entry}", method = RequestMethod.GET)
    public Collection<Unit> getAllForEntry(@PathVariable("entry") Long entry){
        return unitService.getAll();
    }
}
