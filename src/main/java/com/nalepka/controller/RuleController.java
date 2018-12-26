package com.nalepka.controller;

import com.nalepka.model.Nation;
import com.nalepka.model.Option;
import com.nalepka.model.Rule;
import com.nalepka.model.Weapon;
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

    @RequestMapping(value = "/{weapon}", method = RequestMethod.GET)
    public Collection<Rule> getAllForWeapon(@PathVariable("weapon") Long weapon){
        return ruleService.getAll();
    }

    @RequestMapping(value = "/{unit}", method = RequestMethod.GET)
    public Collection<Rule> getAllForUnit(@PathVariable("unit") Long unit){
        return ruleService.getAll();
    }
}
