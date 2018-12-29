package com.nalepka.repository.impl;

import com.nalepka.model.Entry;
import com.nalepka.model.Selector;
import com.nalepka.repository.AbstractGenericDao;
import com.nalepka.repository.EntryDao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class EntryDaoImpl extends AbstractGenericDao<Entry> implements EntryDao {
    @PersistenceContext
    private EntityManager em;

}
