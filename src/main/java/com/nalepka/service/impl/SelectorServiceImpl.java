package com.nalepka.service.impl;

import com.nalepka.model.Entry;
import com.nalepka.model.Nation;
import com.nalepka.model.Selector;
import com.nalepka.repository.NationDao;
import com.nalepka.repository.SelectorDao;
import com.nalepka.service.SelectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectorServiceImpl implements SelectorService {
    private SelectorDao selectorDao;
    private NationDao nationDao;

    @Autowired
    public SelectorServiceImpl(SelectorDao selectorDao, NationDao nationDao) {
        this.selectorDao = selectorDao;
        this.nationDao = nationDao;
    }

    @Override
    public List<Selector> getAll() {
        return null;
    }

    @Override
    public Selector getById(Long id) {
        return selectorDao.findById(id).get();
    }

    @Override
    public Selector update(Selector selector) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public List<Selector> getForNation(String nationName) {
        return selectorDao.findForNationName(nationName);
    }
}
