package com.nalepka.controller;

import com.nalepka.model.Entry;
import com.nalepka.service.impl.EntryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/entry")
public class EntryController {

    @Autowired
    EntryServiceImpl entryService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Entry> getAll(){
        return entryService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Entry getById(@PathVariable("id") Long id){
        return entryService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Entry> add(Entry nation){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<Entry> modify(@RequestBody Entry nation){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
