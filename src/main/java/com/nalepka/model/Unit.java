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
    private Integer iCost;
    @Column(name = "r_cost")
    private Integer rCost;
    @Column(name = "v_cost")
    private Integer vCost;
    @Column(name = "additional_i_cost")
    private Integer IAdditionalCost;
    @Column(name = "additional_r_cost")
    private Integer RAdditionalCost;
    @Column(name = "additional_v_cost")
    private Integer VAdditionalCost;
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
        cost.put(ExperienceLevel.INEXPERIENCED, getIAdditionalCost());
        cost.put(ExperienceLevel.REGULAR, getRAdditionalCost());
        cost.put(ExperienceLevel.VETERAN, getVAdditionalCost());

        return cost.get(level);
    }

    public Integer getCost(ExperienceLevel level){
        Map<ExperienceLevel, Integer> cost = new HashMap<>();
        cost.put(ExperienceLevel.INEXPERIENCED, getICost());
        cost.put(ExperienceLevel.REGULAR, getRCost());
        cost.put(ExperienceLevel.VETERAN, getVCost());

        return cost.get(level);
    }
}
