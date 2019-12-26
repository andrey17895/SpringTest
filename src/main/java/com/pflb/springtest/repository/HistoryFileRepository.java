package com.pflb.springtest.repository;

import com.pflb.springtest.model.entity.HistoryFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryFileRepository extends JpaRepository<HistoryFile, Long> {

}
