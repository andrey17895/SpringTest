package com.pflb.SpringTest.data.repositories;

import com.pflb.SpringTest.data.entities.TestProfile;
import org.springframework.data.repository.CrudRepository;

public interface TestProfileRepository extends CrudRepository<TestProfile, Long> {
}
