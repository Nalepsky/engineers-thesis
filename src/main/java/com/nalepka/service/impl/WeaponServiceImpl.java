package com.nalepka.service.impl;

import com.nalepka.model.Unit;
import com.nalepka.model.Weapon;
import com.nalepka.repository.UnitDao;
import com.nalepka.repository.WeaponDao;
import com.nalepka.service.WeaponService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeaponServiceImpl implements WeaponService {
    private WeaponDao weaponDao;
    private UnitDao unitDao;

    public WeaponServiceImpl(WeaponDao weaponDao, UnitDao unitDao) {
        this.weaponDao = weaponDao;
        this.unitDao = unitDao;
    }

    @Override
    public List<Weapon> getAll() {
        List<Weapon> weapons = new ArrayList<>();
        System.out.println(weaponDao.findAll().toString());
        weaponDao.findAll()
                .forEach(weapons::add);
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

    @Override
    public List<Weapon> getForUnitId(Long unitId) {
        Unit unit = unitDao.findById(unitId).get();
        //TODO add unitweapon table...
        return null;
    }
}
