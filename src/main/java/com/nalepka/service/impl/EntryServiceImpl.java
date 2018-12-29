package com.nalepka.service.impl;

import com.nalepka.model.Entry;
import com.nalepka.model.Selector;
import com.nalepka.repository.SelectorDao;
import com.nalepka.service.EntryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {
    private SelectorDao selectorDao;

    public EntryServiceImpl(SelectorDao selectorDao) {
        this.selectorDao = selectorDao;
    }

    @Override
    public List<Entry> getAll() {
        return null;
    }

    @Override
    public Entry getById(Long id) {
        return null;
    }

    @Override
    public Entry update(Entry entry) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }

    @Override
    public List<Entry> getEntriesForSelectorId(Long selectorId) {
        Selector selector = selectorDao.findById(selectorId).get();
        return selector.getEntries();
    }
}
