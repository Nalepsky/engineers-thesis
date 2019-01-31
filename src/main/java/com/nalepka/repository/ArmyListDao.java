package com.nalepka.repository;

import com.nalepka.model.ArmyList;
import com.nalepka.model.Entry;
import org.springframework.data.repository.CrudRepository;

public interface ArmyListDao extends CrudRepository<ArmyList, Long> {
}
