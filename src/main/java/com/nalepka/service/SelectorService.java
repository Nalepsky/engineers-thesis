package com.nalepka.service;

import com.nalepka.model.Nation;
import com.nalepka.model.Selector;

import java.util.List;

public interface SelectorService extends GenericService<Selector> {
    List<Selector> getForNation(String nationName);
}
