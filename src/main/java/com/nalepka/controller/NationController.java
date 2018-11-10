package com.nalepka.controller;

import com.nalepka.model.Nation;
import com.nalepka.model.Weapon;
import com.nalepka.repository.NationDao;
import com.nalepka.repository.impl.NationDaoImpl;
import com.nalepka.service.impl.NationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/nation")
public class NationController {
    @Autowired
    NationServiceImpl nationService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Nation> getAll(){
        return nationService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Nation getById(@PathVariable("id") Long id){
        return nationService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Nation> add(Nation nation){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<Nation> modify(@RequestBody Nation nation){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
