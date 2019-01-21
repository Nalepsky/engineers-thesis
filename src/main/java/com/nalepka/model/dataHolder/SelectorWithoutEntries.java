package com.nalepka.model.dataHolder;

import com.nalepka.model.Nation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SelectorWithoutEntries {
    private Long id;
    private String name;
    private Nation nation;
}
