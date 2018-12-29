package com.nalepka.service.impl;

import com.nalepka.model.Rule;
import com.nalepka.model.Unit;
import com.nalepka.repository.RuleDao;
import com.nalepka.repository.UnitDao;
import com.nalepka.service.RuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {
    private RuleDao ruleDao;
    private UnitDao unitDao;

    public RuleServiceImpl(RuleDao ruleDao, UnitDao unitDao) {
        this.ruleDao = ruleDao;
        this.unitDao = unitDao;
    }

    @Override
    public List<Rule> getAll() {
        return null;
    }

    @Override
    public Rule getById(Long id) {
        return null;
    }

    @Override
    public Rule update(Rule rule) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Rule> getForUnitId(Long unitId) {
        Unit unit = unitDao.findById(unitId).get();
        return unit.getRules();
    }
}
