package com.nalepka.Service;

import com.nalepka.Dao.WeaponDao;
import com.nalepka.Entity.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class WeaponService {
    @Autowired
    private WeaponDao weaponDao;

    public Collection<Weapon> getAllWeapons(){
        return weaponDao.getAllWeapons();
    }

    public Weapon getWeaponById(Integer id){
        return weaponDao.getWeaponById(id);
    }
}
