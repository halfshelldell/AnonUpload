package com.ironyard.services;

import com.ironyard.entities.AnonFile;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by illladell on 6/27/16.
 */
public interface AnonFileRepository extends CrudRepository<AnonFile, Integer> {
    Iterable<AnonFile> findByOrderByIdAsc();
    Iterable<AnonFile> findByIsPermanentFalseOrderByIdAsc();
}
