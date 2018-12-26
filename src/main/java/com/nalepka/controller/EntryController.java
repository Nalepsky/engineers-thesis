package com.nalepka.controller;

import com.nalepka.model.Entry;
import com.nalepka.model.Selector;
import com.nalepka.service.impl.EntryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/{selector}", method = RequestMethod.GET)
    public Collection<Entry> getAllForSelector(@PathVariable("selector") Long selector){
        return entryService.getAll();
    }
}
