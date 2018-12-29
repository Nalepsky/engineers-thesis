package com.nalepka.service;

import com.nalepka.model.Entry;

import java.util.List;

public interface EntryService extends GenericService<Entry> {
    List<Entry> getEntriesForSelectorId(Long selectorId);
}
