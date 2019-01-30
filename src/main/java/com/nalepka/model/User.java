package com.nalepka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @Column(name = "pass")
    private String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Boolean validatePassword(String password){
        return this.password.equals(password);
    }
}
