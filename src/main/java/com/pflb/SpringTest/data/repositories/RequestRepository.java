package com.pflb.SpringTest.data.repositories;

import com.pflb.SpringTest.data.entities.Request;
import org.springframework.data.repository.CrudRepository;

public interface RequestRepository extends CrudRepository<Request, Long> {
}
