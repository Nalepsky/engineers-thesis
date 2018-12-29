package com.nalepka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Selector {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="nation_id")
    private Nation nation;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="entryselector",
        joinColumns = @JoinColumn(name = "selector_id"),
        inverseJoinColumns = @JoinColumn(name = "entry_id")
    )
    private List<Entry> entries;

    @Override
    public String toString() {
        return "Selector{" +
                "name='" + name + '\'' +
                ", nation=" + nation +
                '}';
    }
}
