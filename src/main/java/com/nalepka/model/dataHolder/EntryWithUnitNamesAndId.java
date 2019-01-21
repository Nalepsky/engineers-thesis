package com.nalepka.model.dataHolder;

import com.nalepka.utils.UnitType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryWithUnitNamesAndId {
    private Long id;
    private Long min;
    private Long max;
    private UnitType type;
    private List<UnitNameAndId> units;
}
