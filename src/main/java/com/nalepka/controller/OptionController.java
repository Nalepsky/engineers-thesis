package com.nalepka.controller;

import com.nalepka.model.Nation;
import com.nalepka.model.Option;
import com.nalepka.service.impl.OptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/option")
public class OptionController {
    @Autowired
    OptionServiceImpl optionService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Option> getAll(){
        return optionService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Option getById(@PathVariable("id") Long id){
        return optionService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Option> add(Option option){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<Option> modify(@RequestBody Option option){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
