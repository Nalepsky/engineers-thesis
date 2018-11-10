package com.nalepka.repository;

import com.nalepka.model.Rule;
import com.nalepka.model.Weapon;
import org.springframework.data.repository.CrudRepository;

public interface RuleDao extends CrudRepository<Rule, Long> {

}
