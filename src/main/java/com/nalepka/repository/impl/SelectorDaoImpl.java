package com.nalepka.repository.impl;

import com.nalepka.model.Nation;
import com.nalepka.model.Selector;
import com.nalepka.model.Weapon;
import com.nalepka.repository.AbstractGenericDao;
import com.nalepka.repository.SelectorDao;
import com.nalepka.repository.WeaponDao;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
public class SelectorDaoImpl extends AbstractGenericDao<Selector> implements SelectorDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Selector> findForNationName(String nationName) {
        em = getEntityManager();
        try{
            Query query = em.createQuery(
                    "SELECT s FROM Selector s LEFT JOIN s.nation WHERE s.nation.name = :name",
                    Selector.class
            );
            query.setParameter("name", nationName);
            return (List)query.getResultList();
        }catch (Exception e){

        }
        return null;
    }
}
