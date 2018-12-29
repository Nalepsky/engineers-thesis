package com.nalepka.service.impl;

import com.nalepka.model.Option;
import com.nalepka.model.Unit;
import com.nalepka.repository.OptionDao;
import com.nalepka.repository.UnitDao;
import com.nalepka.service.OptionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {
    private OptionDao optionDao;
    private UnitDao unitDao;

    public OptionServiceImpl(OptionDao optionDao, UnitDao unitDao) {
        this.optionDao = optionDao;
        this.unitDao = unitDao;
    }

    @Override
    public List<Option> getAll() {
        return null;
    }

    @Override
    public Option getById(Long id) {
        return null;
    }

    @Override
    public Option update(Option option) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Option> getForUnitId(Long unitId) {
        Unit unit = unitDao.findById(unitId).get();
        return unit.getOptions();
    }
}
