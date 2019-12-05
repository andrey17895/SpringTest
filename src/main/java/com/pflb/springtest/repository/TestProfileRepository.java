package com.pflb.springtest.repository;

import com.pflb.springtest.model.entity.TestProfile;
import org.springframework.data.repository.CrudRepository;

public interface TestProfileRepository extends CrudRepository<TestProfile, Long> {
}
