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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArmyListServiceImpl implements ArmyListService {
    private ArmyListDao armyListDao;

    @Autowired
    public ArmyListServiceImpl(ArmyListDao armyListDao) {
        this.armyListDao = armyListDao;
    }

    public List<String> getAllById(Long id){
        final List<ArmyList> armyLists = getAll()
                .stream()
                .filter(armyList -> armyList.getUserId().equals(id))
                .collect(Collectors.toList());

        final List<String> result = new ArrayList<>();

        armyLists.forEach(armyList -> result.add(armyList.getSelector()));

        return  result;
    }

    @Override
    public List<ArmyList> getAll() {
        return (List<ArmyList>) this.armyListDao.findAll();
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
