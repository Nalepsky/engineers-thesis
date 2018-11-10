package com.nalepka.controller;

import com.nalepka.model.Rule;
import com.nalepka.model.Selector;
import com.nalepka.service.impl.SelectorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/selector")
public class SelectorController {
    @Autowired
    SelectorServiceImpl selectorService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Selector> getAll(){
        return selectorService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Selector getById(@PathVariable("id") Long id){
        return selectorService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Selector> add(Selector selector){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<Selector> modify(@RequestBody Selector selector){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
