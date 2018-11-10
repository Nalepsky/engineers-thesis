package com.nalepka.controller;

import com.nalepka.model.Nation;
import com.nalepka.model.Option;
import com.nalepka.model.Rule;
import com.nalepka.service.impl.RuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/rule")
public class RuleController {
    @Autowired
    RuleServiceImpl ruleService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Rule> getAll(){
        return ruleService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Rule getById(@PathVariable("id") Long id){
        return ruleService.getById(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Rule> add(Rule rule){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public ResponseEntity<Rule> modify(@RequestBody Rule rule){
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
