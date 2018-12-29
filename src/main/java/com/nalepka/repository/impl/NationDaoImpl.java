package com.nalepka.repository.impl;

import com.nalepka.model.Nation;
import com.nalepka.model.Selector;
import com.nalepka.model.Weapon;
import com.nalepka.repository.AbstractGenericDao;
import com.nalepka.repository.NationDao;
import com.nalepka.repository.WeaponDao;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class NationDaoImpl extends AbstractGenericDao<Nation> implements NationDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Nation> findByName(String name) {
        em = getEntityManager();
        try{
            Query query = em.createQuery(
                    "SELECT n FROM Nation n WHERE n.name = :name",
                    Nation.class
            );
            query.setParameter("name", name);
            return Optional.of((Nation) query.getResultList());
        }catch (Exception e){

        }
        return Optional.empty();
    }
}
