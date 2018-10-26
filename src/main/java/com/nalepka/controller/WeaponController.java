package com.nalepka.controller;

import com.nalepka.model.Weapon;
import com.nalepka.service.WeaponService;
import com.nalepka.service.WeaponServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/weapons")
public class WeaponController {
    @Autowired
    private WeaponServiceImpl weaponService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Weapon> getAllWeapons(){
        return weaponService.getAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Weapon getWeaponById(@PathVariable("id") Long id){
        return weaponService.getById(id);
    }
}
