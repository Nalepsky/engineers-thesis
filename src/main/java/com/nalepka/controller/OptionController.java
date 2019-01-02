package com.nalepka.controller;

import com.nalepka.model.Nation;
import com.nalepka.model.Option;
import com.nalepka.service.impl.OptionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @RequestMapping(value = "unit/{unitId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Option>> getAllForUnitId(@PathVariable("unitId") Long unitId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(optionService.getForUnitId(unitId), headers, HttpStatus.ACCEPTED);
    }
}
