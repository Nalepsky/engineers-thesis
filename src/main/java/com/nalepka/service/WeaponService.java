package com.nalepka.service;

import com.nalepka.model.Weapon;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface WeaponService {
    List<Weapon> getAll();
    Weapon getById(Long id);
    Weapon update(Weapon weapon);
    void delete(Long id);

}
