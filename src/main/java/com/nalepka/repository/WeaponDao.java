package com.nalepka.repository;

import com.nalepka.model.Weapon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface WeaponDao extends CrudRepository<Weapon, Long> {

}
