package com.nalepka.service.impl;

import com.nalepka.model.Rule;
import com.nalepka.model.Unit;
import com.nalepka.model.Weapon;
import com.nalepka.repository.RuleDao;
import com.nalepka.repository.UnitDao;
import com.nalepka.repository.WeaponDao;
import com.nalepka.service.RuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {
    private RuleDao ruleDao;
    private UnitDao unitDao;
    private WeaponDao weaponDao;

    public RuleServiceImpl(RuleDao ruleDao, UnitDao unitDao, WeaponDao weaponDao) {
        this.ruleDao = ruleDao;
        this.unitDao = unitDao;
        this.weaponDao = weaponDao;
    }

    @Override
    public List<Rule> getAll() {
        return null;
    }

    @Override
    public Rule getById(Long id) {
        return null;
    }

    @Override
    public Rule update(Rule rule) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Rule> getForUnitId(Long unitId) {
        Unit unit = unitDao.findById(unitId).get();
        return unit.getRules();
    }

    @Override
    public List<Rule> getForWeaponId(Long weaponId) {
        Weapon weapon = weaponDao.findById(weaponId).get();
        return weapon.getRules();
    }
}
