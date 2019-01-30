package com.nalepka.repository.impl;

import com.nalepka.model.Entry;
import com.nalepka.model.Unit;
import com.nalepka.model.User;
import com.nalepka.repository.AbstractGenericDao;
import com.nalepka.repository.EntryDao;
import com.nalepka.repository.UserDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class UserDaoImpl extends AbstractGenericDao<User> implements UserDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public boolean checkIfUserExist(String email) {
        em = getEntityManager();
        try{
            Query query = em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email",
                    User.class
            );
            query.setParameter("email", email);

            if(!query.getResultList().isEmpty()){
                return true;
            }
        }catch (Exception e){
        }
        return false;
    }

    @Override
    public Long validateCredentials(String email, String password) {
        em = getEntityManager();
        try{
            //TODO make it more efficient, Select only pass, not entire User object
            Query query = em.createQuery(
                    "SELECT u FROM User u WHERE u.email = :email",
                    User.class
            );
            query.setParameter("email", email);

            User user = (User) query.getSingleResult();

            if(user.validatePassword(password)){
                return user.getId();
            }
        }catch (Exception e){
        }
        return -1L;
    }
}
