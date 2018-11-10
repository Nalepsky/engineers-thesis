package com.nalepka.repository;

import com.nalepka.model.Nation;
import com.nalepka.model.Weapon;
import org.springframework.data.repository.CrudRepository;

public interface NationDao extends CrudRepository<Nation, Long> {

}
