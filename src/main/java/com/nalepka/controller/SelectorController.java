package com.nalepka.controller;

import com.nalepka.model.Rule;
import com.nalepka.model.Selector;
import com.nalepka.service.impl.SelectorServiceImpl;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/selector")
public class SelectorController {
    @Autowired
    SelectorServiceImpl selectorService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Selector> getAll(){
        return selectorService.getAll();
    }

    @RequestMapping(value = "nation/{nation}", method = RequestMethod.GET)
    public ResponseEntity<Collection<String>> getAllForNation(@PathVariable("nation") String nation) {
        List <String> result = new ArrayList();
        List selectors = selectorService.getForNation(nation);
        selectors.forEach(s -> result.add(selectors.toString()));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(result, headers, HttpStatus.ACCEPTED);
    }
}
