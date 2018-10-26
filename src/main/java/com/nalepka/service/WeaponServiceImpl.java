package com.nalepka.service;

import com.nalepka.model.Weapon;
import com.nalepka.repository.WeaponDao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeaponServiceImpl implements WeaponService {
    private WeaponDao weaponDao;

    @Autowired
    public WeaponServiceImpl(WeaponDao weaponDao) {
        this.weaponDao = weaponDao;
    }

    @Override
    public List<Weapon> getAll() {
        List<Weapon> weapons = new ArrayList<>();
        weaponDao.findAll().forEach(weapons::add);
        return weapons;
    }

    @Override
    public Weapon getById(Long id) {
        return weaponDao.findById(id).get();
    }

    @Override
    public Weapon update(Weapon weapon) {
        return weaponDao.save(weapon);
    }

    @Override
    public void delete(Long id) {
        weaponDao.deleteById(id);
    }
}
