package com.nalepka.repository;

import com.nalepka.model.Entry;
import org.springframework.data.repository.CrudRepository;

public interface EntryDao extends CrudRepository<Entry, Long> {
}
