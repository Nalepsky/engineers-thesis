package com.nalepka.repository.impl;

import com.nalepka.model.Option;
import com.nalepka.model.Weapon;
import com.nalepka.repository.AbstractGenericDao;
import com.nalepka.repository.OptionDao;
import com.nalepka.repository.WeaponDao;
import net.bytebuddy.dynamic.DynamicType;
import org.springframework.stereotype.Repository;

@Repository
public class OptionDaoImpl extends AbstractGenericDao<Option> implements OptionDao {

}
