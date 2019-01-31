package com.nalepka.repository.impl;

import com.nalepka.model.ArmyList;
import com.nalepka.model.Entry;
import com.nalepka.repository.AbstractGenericDao;
import com.nalepka.repository.ArmyListDao;
import com.nalepka.repository.EntryDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ArmyListDaoImpl extends AbstractGenericDao<ArmyList> implements ArmyListDao {
    @PersistenceContext
    private EntityManager em;

}
