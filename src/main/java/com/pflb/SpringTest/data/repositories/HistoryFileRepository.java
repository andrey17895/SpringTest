package com.pflb.SpringTest.data.repositories;

import com.pflb.SpringTest.data.entities.HistoryFile;
import org.springframework.data.repository.CrudRepository;

public interface HistoryFileRepository extends CrudRepository<HistoryFile, Long> {

}
