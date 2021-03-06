package com.pflb.springtest.repository;

import com.pflb.springtest.model.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByTestProfileId(Long testProfileId);
    Optional<Request> findByIdAndTestProfileId(Long id, Long testProfileId);
}
