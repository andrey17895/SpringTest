package com.pflb.SpringTest.rest;

import com.pflb.SpringTest.data.entities.TestProfile;
import com.pflb.SpringTest.data.repositories.TestProfileRepository;
import com.pflb.SpringTest.rest.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestProfileController {

    @Autowired
    private TestProfileRepository testProfileRepository;

    @GetMapping(value = "/testProfile")
    @ResponseBody
    public Iterable<TestProfile> getAllProfiles() {
        return testProfileRepository.findAll();
    }

    @GetMapping(value = "/testProfile/{id}")
    @ResponseBody
    public TestProfile getTestProfileById(@PathVariable Long id) {
        return testProfileRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Test Profile not found: id=" + id));
    }

    @PostMapping(value = "/testProfile")
    @ResponseBody
    public TestProfile postTestProfile(@RequestBody TestProfile newTestProfile) {
        return testProfileRepository.save(newTestProfile);
    }

    @PutMapping(value = "/testProfile/{id}")
    @ResponseBody
    public TestProfile putTestProfileRequests(@RequestBody TestProfile newTestProfile, @PathVariable Long id) {
        return testProfileRepository.findById(id)
                .map(testProfile -> {
                    testProfile.setRequests(newTestProfile.getRequests());
                    newTestProfile.getRequests().forEach(x -> x.setTestProfile(testProfile));
                    return testProfileRepository.save(testProfile);
                })
                .orElseGet(() -> {
//                    newTestProfile.setId(id);
                    newTestProfile.getRequests().forEach(x -> x.setTestProfile(newTestProfile));
                    return testProfileRepository.save(newTestProfile);
//                    return testProfileRepository.save(newTestProfile);
                });
    }

    @DeleteMapping(value = "/testProfile")
    public void deleteAll() {
        testProfileRepository.deleteAll();
    }
}
