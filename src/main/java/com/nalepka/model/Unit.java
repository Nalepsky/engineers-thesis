package com.nalepka.model;

import com.nalepka.utils.ExperienceLevel;
import com.nalepka.utils.UnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Enumerated(EnumType.STRING)
    private UnitType type;
    private String composition;
    @Column(name = "base_number")
    private Integer baseNumber;
    @Column(name = "max_number")
    private Integer maxNumber;
    @Column(name = "i_cost")
    private Integer inexpCost;
    @Column(name = "r_cost")
    private Integer regCost;
    @Column(name = "v_cost")
    private Integer vetCost;
    @Column(name = "additional_i_cost")
    private Integer inexpAdditionalCost;
    @Column(name = "additional_r_cost")
    private Integer regAdditionalCost;
    @Column(name = "additional_v_cost")
    private Integer vetAdditionalCost;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="ruleunit",
            joinColumns = @JoinColumn(name = "unit_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private List<Rule> rules;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="unitoption",
            joinColumns = @JoinColumn(name = "unit_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private List<Option> options;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="unitweapon",
            joinColumns = @JoinColumn(name = "unit_id"),
            inverseJoinColumns = @JoinColumn(name = "weapon_id")
    )
    private List<Weapon> weapons;

    public Integer getAdditionalCost(ExperienceLevel level){
        Map<ExperienceLevel, Integer> cost = new HashMap<>();
        cost.put(ExperienceLevel.INEXPERIENCED, getInexpAdditionalCost());
        cost.put(ExperienceLevel.REGULAR, getRegAdditionalCost());
        cost.put(ExperienceLevel.VETERAN, getVetAdditionalCost());

        return cost.get(level);
    }

    public Integer getCost(ExperienceLevel level){
        Map<ExperienceLevel, Integer> cost = new HashMap<>();
        cost.put(ExperienceLevel.INEXPERIENCED, getInexpCost());
        cost.put(ExperienceLevel.REGULAR, getRegCost());
        cost.put(ExperienceLevel.VETERAN, getVetCost());

        return cost.get(level);
    }
}
