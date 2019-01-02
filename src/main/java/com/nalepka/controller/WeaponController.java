package com.nalepka.controller;

import com.nalepka.model.Unit;
import com.nalepka.model.Weapon;
import com.nalepka.service.impl.WeaponServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/weapon")
public class WeaponController {
    @Autowired
    private WeaponServiceImpl weaponService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Weapon>> getAll(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(weaponService.getAll(), headers, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "unit/{unitId}", method = RequestMethod.GET)
    public  ResponseEntity<Collection<Weapon>> getAllForUnitId(@PathVariable("unitId") Long unitId){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(weaponService.getForUnitId(unitId), headers, HttpStatus.ACCEPTED);
    }

}
