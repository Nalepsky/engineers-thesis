package com.nalepka.service.impl;

import com.nalepka.model.Unit;
import com.nalepka.repository.UnitDao;
import com.nalepka.service.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnitServiceImpl implements UnitService {
    private UnitDao unitDao;

    @Override
    public List<Unit> getAll() {
        return null;
    }

    @Override
    public Unit getById(Long id) {
        return unitDao.findById(id).get();
    }

    @Override
    public Unit update(Unit unit) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
