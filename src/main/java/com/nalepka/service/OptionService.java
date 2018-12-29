package com.nalepka.service;

import com.nalepka.model.Option;

import java.util.List;

public interface OptionService extends GenericService<Option> {
    List<Option> getForUnitId(Long unitId);
}
