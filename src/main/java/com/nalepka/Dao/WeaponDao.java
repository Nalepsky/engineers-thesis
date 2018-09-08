package com.nalepka.Dao;

import com.nalepka.Entity.Weapon;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WeaponDao {

    private static Map<Integer, Weapon> weapons;

    static {
        weapons = new HashMap<Integer, Weapon>(){
            {
               put(1, new Weapon(1, "SMG", 12, 2, 0));
               put(2, new Weapon(2, "Rifle", 24, 1, 0));
               put(3, new Weapon(3, "Pistol", 6, 1, 0));
            }
        };
    }

    public Collection<Weapon> getAllWeapons(){
        return this.weapons.values();
    }

    public Weapon getWeaponById(Integer id){
        return this.weapons.get(id);
    }
}
