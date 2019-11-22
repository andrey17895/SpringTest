package com.pflb.springtest.repository;

import com.pflb.springtest.entity.TestProfileEntity;
import org.springframework.data.repository.CrudRepository;

public interface TestProfileRepository extends CrudRepository<TestProfileEntity, Long> {
}
