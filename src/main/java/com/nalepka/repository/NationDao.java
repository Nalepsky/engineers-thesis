package com.nalepka.repository;

import com.nalepka.model.Nation;
import com.nalepka.model.Weapon;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface NationDao extends CrudRepository<Nation, Long> {
    public Optional<Nation> findByName(String name);
}
