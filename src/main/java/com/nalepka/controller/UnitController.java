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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Unit getById(@PathVariable("id") Long id){
        return unitService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Unit> add(Unit unit){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<Unit> modify(@RequestBody Unit unit){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
