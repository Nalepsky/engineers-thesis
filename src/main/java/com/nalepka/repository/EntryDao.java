package com.nalepka.repository;

import com.nalepka.model.Entry;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EntryDao extends CrudRepository<Entry, Long> {
}
