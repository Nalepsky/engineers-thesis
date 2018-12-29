package com.nalepka.service;

import com.nalepka.model.Weapon;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WeaponService extends GenericService<Weapon> {
    List<Weapon> getForUnitId(Long unitId);
}
