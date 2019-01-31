package com.nalepka.service.impl;

import com.nalepka.model.ArmyList;
import com.nalepka.model.Entry;
import com.nalepka.model.Selector;
import com.nalepka.repository.ArmyListDao;
import com.nalepka.repository.EntryDao;
import com.nalepka.repository.SelectorDao;
import com.nalepka.service.ArmyListService;
import com.nalepka.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArmyListServiceImpl implements ArmyListService {
    private ArmyListDao armyListDao;

    @Autowired
    public ArmyListServiceImpl(ArmyListDao armyListDao) {
        this.armyListDao = armyListDao;
    }

    @Override
    public List<ArmyList> getAll() {
        return null;
    }

    @Override
    public ArmyList getById(Long id) {
        return null;
    }

    @Override
    public ArmyList update(Long userId, String selector) {
        return update(new ArmyList(userId, selector));
    }

    @Override
    public ArmyList update(ArmyList armyList) {
        return this.armyListDao.save(armyList);
    }

    @Override
    public void delete(Long id) {

    }
}
