package com.nalepka.service;

import com.nalepka.model.Rule;

import java.util.List;

public interface RuleService extends GenericService<Rule> {
    List<Rule> getForUnitId(Long unitId);
    List<Rule> getForWeaponId(Long weaponId);
}
