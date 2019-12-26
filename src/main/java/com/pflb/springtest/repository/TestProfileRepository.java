package com.pflb.springtest.repository;

import com.pflb.springtest.model.entity.TestProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestProfileRepository extends JpaRepository<TestProfile, Long> {
}
