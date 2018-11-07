package com.nalepka.repository;

import org.springframework.data.repository.CrudRepository;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class AbstractGenericDao<T> implements CrudRepository<T, Long> {
    //@PersistenceContext(unitName = "weapon-unit", type = PersistenceContextType.EXTENDED)
    @PersistenceContext
    private EntityManager em;
    final private Class<T> entityClass;

    protected AbstractGenericDao() {
        entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    @Override
    public <S extends T> S save(S s) {
        em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(s);
            em.getTransaction().commit();
            return s;
        }catch(Exception e){

        }
        return null;
    }

    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> iterable) {
        //probably won't be used in application
        return null;
    }

    @Override
    public Optional<T> findById(Long aLong) {
        em = getEntityManager();
        try{
            return Optional.of((T) em.find(entityClass, aLong));
        }catch(Exception e){

        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        //probably won't be used in application
        return false;
    }

    @Override
    public Iterable<T> findAll() {
        em = getEntityManager();
        try{
            Query query = em.createNativeQuery(
                    "SELECT * FROM " + entityClass.getName());
            return (List)query.getResultList();
        }catch (Exception e){

        }
        return null;
    }

    @Override
    public Iterable<T> findAllById(Iterable<Long> iterable) {
        //probably wont be used in application
        return null;
    }

    @Override
    public long count() {
        em = getEntityManager();
        try{
            Query q = em.createNativeQuery("SELECT COUNT * FROM " + entityClass.getName());
            return (long)q.getSingleResult();
        }catch(Exception e){

        }
        return -1;
    }

    @Override
    public void deleteById(Long aLong) {
        em = getEntityManager();
        try{
            T t = em.find(entityClass, aLong);
            delete(t);
        }catch (Exception e){

        }
    }

    @Override
    public void delete(T t) {
        em = getEntityManager();
        try{
            em.getTransaction().begin();
            em.remove(t);
            em.getTransaction().commit();
        }catch (Exception e){

        }
    }

    @Override
    public void deleteAll(Iterable<? extends T> iterable) {
        iterable.forEach(i -> delete(i));
    }

    @Override
    public void deleteAll() {
        em = getEntityManager();
        try{
            Query q = em.createQuery(
                    "DELETE FROM " + entityClass.getName(),
                    entityClass
            );
            q.executeUpdate();
        }catch(Exception e){

        }
    }

    private EntityManager getEntityManager(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("armycreator");
        return emf.createEntityManager();
    }
}
