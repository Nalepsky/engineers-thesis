package com.nalepka.repository;

import com.nalepka.model.Option;
import com.nalepka.model.Weapon;
import org.springframework.data.repository.CrudRepository;

public interface OptionDao extends CrudRepository<Option, Long> {

}
