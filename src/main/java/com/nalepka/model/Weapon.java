package com.nalepka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String range;
    private String shots;
    private String penetration;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "RuleWeapon",
            joinColumns = { @JoinColumn(name = "weapon_id") },
            inverseJoinColumns = { @JoinColumn(name = "rule_id") })
    private List<Rule> rules;

    public String getRulesNames(){
        StringBuilder rulesNames = new StringBuilder();
        getRules().forEach(r -> rulesNames.append(r.getName()).append(", "));

        String result = rulesNames.toString();

        if(result.isEmpty()){
            return "-";
        }

        return  result.substring(0, result.length() - 2);
    }
}
