package com.pflb.SpringTest.data.repositories;

import com.pflb.SpringTest.data.entities.Request;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends CrudRepository<Request, Long> {
    List<Request> findByTestProfileId(Long testProfileId);
    Optional<Request> findByIdAndTestProfileId(Long id, Long testProfileId);
}
