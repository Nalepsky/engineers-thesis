package com.nalepka.repository.impl;

import com.nalepka.model.Unit;
import com.nalepka.model.Weapon;
import com.nalepka.repository.AbstractGenericDao;
import com.nalepka.repository.UnitDao;
import com.nalepka.repository.WeaponDao;
import org.springframework.stereotype.Repository;

@Repository
public class UnitDaoImpl extends AbstractGenericDao<Unit> implements UnitDao {

}
