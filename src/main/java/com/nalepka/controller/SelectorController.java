package com.nalepka.controller;

import com.nalepka.model.Rule;
import com.nalepka.model.Selector;
import com.nalepka.service.impl.SelectorServiceImpl;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/selector")
public class SelectorController {
    @Autowired
    SelectorServiceImpl selectorService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Selector> getAll(){
        return selectorService.getAll();
    }

    @RequestMapping(value = "/{nation}", method = RequestMethod.GET)
    public Collection<Selector> getAllForNation(@PathVariable("nation") Long nation){
        return selectorService.getAll();
    }
}
