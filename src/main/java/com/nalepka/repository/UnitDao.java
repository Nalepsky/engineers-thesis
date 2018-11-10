package com.nalepka.repository;

import com.nalepka.model.Unit;
import com.nalepka.model.Weapon;
import org.springframework.data.repository.CrudRepository;

public interface UnitDao extends CrudRepository<Unit, Long> {

}
