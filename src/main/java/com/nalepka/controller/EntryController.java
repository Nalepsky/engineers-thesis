package com.nalepka.controller;

import com.nalepka.model.Entry;
import com.nalepka.service.impl.EntryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

    @RequestMapping(value = "selector/{selectorId}", method = RequestMethod.GET)
    public ResponseEntity<Collection<Entry>> getAllForSelectorId(@PathVariable("selectorId") Long selectorId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(entryService.getEntriesForSelectorId(selectorId), headers, HttpStatus.ACCEPTED);
    }
}
