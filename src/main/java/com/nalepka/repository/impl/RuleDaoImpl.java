package com.nalepka.repository.impl;

import com.nalepka.model.Rule;
import com.nalepka.model.Weapon;
import com.nalepka.repository.AbstractGenericDao;
import com.nalepka.repository.RuleDao;
import com.nalepka.repository.WeaponDao;
import org.springframework.stereotype.Repository;

@Repository
public class RuleDaoImpl extends AbstractGenericDao<Rule> implements RuleDao {

}
