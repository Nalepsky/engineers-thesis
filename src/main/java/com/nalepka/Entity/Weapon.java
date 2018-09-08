package com.nalepka.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weapon {
    private Integer id;
    private String name;
    private Integer range;
    private Integer shots;
    private Integer penetration;

}
