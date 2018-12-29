package com.nalepka.model;

import com.nalepka.utils.UnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long min;
    private Long max;
    @Enumerated(EnumType.STRING)
    private UnitType type;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="entryunit",
            joinColumns = @JoinColumn(name = "entry_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id")
    )
    private List<Unit> units;
}
