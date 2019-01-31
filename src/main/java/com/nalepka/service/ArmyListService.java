package com.nalepka.service;

import com.nalepka.model.ArmyList;
import com.nalepka.model.Entry;

import java.util.List;

public interface ArmyListService extends GenericService<ArmyList> {
    ArmyList update(Long userId, String selector);
}
