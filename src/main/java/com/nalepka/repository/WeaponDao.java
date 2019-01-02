package com.nalepka.repository;

import com.nalepka.model.Weapon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WeaponDao extends CrudRepository<Weapon, Long> {
}
