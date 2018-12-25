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
    private UnitType type;
    @ManyToMany
    @JoinColumn(name="unit_id")
    private List<Unit> units;
}
