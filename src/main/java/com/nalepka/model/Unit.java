package com.nalepka.model;

import com.nalepka.utils.UnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="nation_id")
    private Nation nation;
    private UnitType type;
    private String composition;
    private Integer baseNumber;
    private Integer maxNumber;
    private Integer iCost;
    private Integer rCost;
    private Integer vCost;
    private Integer IAditionalCost;
    private Integer RAdditionalCost;
    private Integer VAdditionalCost;
}
