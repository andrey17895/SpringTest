package com.pflb.springtest.controller;

import com.pflb.springtest.dto.TestProfileDto;
import com.pflb.springtest.service.TestProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TestProfileController {

    private TestProfileService testProfileService;

    @Autowired
    public TestProfileController(TestProfileService testProfileService) {
        this.testProfileService = testProfileService;
    }

    @GetMapping(value = "/testProfile")
    @ResponseBody
    public Iterable<TestProfileDto> getAllProfiles() {
        return testProfileService.getAllProfiles();
    }

    @GetMapping(value = "/testProfile/{id}")
    @ResponseBody
    public TestProfileDto getTestProfileById(@PathVariable Long id) {
        return testProfileService.getTestProfileById(id);
    }

    @PostMapping(value = "/testProfile")
    @ResponseBody
    public TestProfileDto postTestProfile(@RequestBody TestProfileDto newTestProfileDto) {
        return testProfileService.createTestProfile(newTestProfileDto);
    }

    @PutMapping(value = "/testProfile/{id}")
    @ResponseBody
    public TestProfileDto putTestProfile(@RequestBody TestProfileDto newTestProfile, @PathVariable Long id) {
        return testProfileService.updateTestProfile(newTestProfile, id);
    }

    @DeleteMapping(value = "/testProfile")
    public void deleteAll() {
        testProfileService.deleteAll();
    }
}
