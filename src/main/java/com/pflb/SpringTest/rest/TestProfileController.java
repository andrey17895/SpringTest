package com.pflb.SpringTest.rest;

import com.pflb.SpringTest.data.entities.TestProfile;
import com.pflb.SpringTest.data.repositories.TestProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestProfileController {

    @Autowired
    private TestProfileRepository testProfileRepository;

    @GetMapping(value = "/testProfile")
    @ResponseBody
    public Iterable<TestProfile> getAllProfiles() {
        return testProfileRepository.findAll();
    }

    @DeleteMapping(value = "/testProfile")
    public void deleteAll() {
        testProfileRepository.deleteAll();
    }
}
