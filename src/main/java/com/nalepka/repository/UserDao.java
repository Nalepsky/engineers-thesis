package com.nalepka.repository;

import com.nalepka.model.Entry;
import com.nalepka.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<User, Long> {
    boolean checkIfUserExist (String email);
    Long validateCredentials(String email, String password);
}
