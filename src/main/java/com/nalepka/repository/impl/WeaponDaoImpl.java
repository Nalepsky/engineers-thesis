package com.nalepka.repository.impl;

import com.nalepka.model.Weapon;
import com.nalepka.repository.WeaponDao;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
public class WeaponDaoImpl implements WeaponDao {

    @PersistenceContext(unitName = "weapon-unit", type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;

    @Override
    public <S extends Weapon> S save(S s) {
        return null;
    }

    @Override
    public <S extends Weapon> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Weapon> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Weapon> findAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("armycreator");
        entityManager = emf.createEntityManager();
        Query query = entityManager.createQuery(
                "SELECT w FROM Weapon w");
        return (List)query.getResultList();
    }

    @Override
    public Iterable<Weapon> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Weapon weapon) {

    }

    @Override
    public void deleteAll(Iterable<? extends Weapon> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
