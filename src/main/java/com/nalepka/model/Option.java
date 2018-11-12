package com.nalepka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name="weapon_id")
    private Weapon weapon;
    @ManyToOne
    @JoinColumn(name="rule_id")
    private Rule rule;
    private String description;
    private Integer cost;

}
