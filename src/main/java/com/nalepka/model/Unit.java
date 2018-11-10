package com.nalepka.model;

import com.nalepka.utils.UnitType;

import javax.persistence.*;

public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name="nation_id")
    private Nation nation;
    private UnitType type;
    private String composition;
    private Integer cost;
    private Integer base_number;
    private Integer max_number;
}
