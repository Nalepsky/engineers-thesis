package com.nalepka.repository.impl;

import com.nalepka.model.Weapon;
import com.nalepka.repository.AbstractGenericDao;
import com.nalepka.repository.WeaponDao;
import org.springframework.stereotype.Repository;

@Repository
public class WeaponDaoImpl extends AbstractGenericDao<Weapon> implements WeaponDao {

}
