package com.nalepka.Controller;

import com.nalepka.Entity.Weapon;
import com.nalepka.Service.WeaponService;
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
    private WeaponService weaponService;

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Weapon> getAllWeapons(){
        return weaponService.getAllWeapons();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Weapon getWeaponById(@PathVariable("id") Integer id){
        return weaponService.getWeaponById(id);
    }
}
