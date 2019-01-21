package com.nalepka.controller;

import com.nalepka.model.Entry;
import com.nalepka.model.dataHolder.EntryWithUnitNamesAndId;
import com.nalepka.service.impl.EntryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
    public ResponseEntity<Collection<EntryWithUnitNamesAndId>> getAllForSelectorId(@PathVariable("selectorId") Long selectorId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");

        List<Entry> entries = entryService.getEntriesForSelectorId(selectorId);
        List<EntryWithUnitNamesAndId> result = new ArrayList<>();

        entries.forEach(e -> result.add(
                new EntryWithUnitNamesAndId(e.getId(), e.getMin(), e.getMax(), e.getType(), e.getUnitsAsUnitNameAndId()))
        );

        return new ResponseEntity<>(result, headers, HttpStatus.ACCEPTED);
    }
}
