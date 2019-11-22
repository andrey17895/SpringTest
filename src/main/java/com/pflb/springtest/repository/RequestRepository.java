package com.pflb.springtest.repository;

import com.pflb.springtest.entity.RequestEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends CrudRepository<RequestEntity, Long> {
    List<RequestEntity> findByTestProfileId(Long testProfileId);
    Optional<RequestEntity> findByIdAndTestProfileId(Long id, Long testProfileId);
}
