package com.nalepka.model.dataHolder;

import com.nalepka.utils.ExperienceLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UnitDataHolder {
    private Long id;
    private ExperienceLevel experienceLevel;
    private Integer numberOfAdditionalModels;
    private Set<OptionsDataHolder> options;
}
