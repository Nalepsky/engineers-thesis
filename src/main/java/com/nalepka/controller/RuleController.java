package com.nalepka.controller;

import com.nalepka.model.Nation;
import com.nalepka.model.Option;
import com.nalepka.model.Rule;
import com.nalepka.model.Weapon;
import com.nalepka.service.impl.RuleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @RequestMapping(value = "weapon/{weaponId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Rule>> getAllForWeapon(@PathVariable("weaponId") Long weaponId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(ruleService.getForWeaponId(weaponId), headers, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "unit/{unitId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Rule>> getAllForUnitId(@PathVariable("unitId") Long unitId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(ruleService.getForUnitId(unitId), headers, HttpStatus.ACCEPTED);
    }
}
