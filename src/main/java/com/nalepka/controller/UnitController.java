package com.nalepka.controller;

import com.nalepka.model.Entry;
import com.nalepka.model.Selector;
import com.nalepka.model.Unit;
import com.nalepka.service.impl.EntryServiceImpl;
import com.nalepka.service.impl.UnitServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/unit")
public class UnitController {
    @Autowired
    UnitServiceImpl unitService;

    @Autowired
    EntryServiceImpl entryService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Unit> getAll(){
        return unitService.getAll();
    }

    @RequestMapping(value = "entry/{entryId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Unit>> getAllForEntry(@PathVariable("entryId") Long entryId){
        Entry entry = entryService.getById(entryId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(entry.getUnits(), headers, HttpStatus.ACCEPTED);
    }
}
