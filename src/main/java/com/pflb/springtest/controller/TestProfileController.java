package com.pflb.springtest.controller;

import com.pflb.springtest.model.dto.profile.TestProfileDto;
import com.pflb.springtest.service.ITestProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TestProfileController {

    private ITestProfileService testProfileService;

    @Autowired
    public TestProfileController(ITestProfileService testProfileService) {
        this.testProfileService = testProfileService;
    }

    @GetMapping(value = "/testProfile")
    @ResponseBody
    public List<TestProfileDto> getAllProfiles() {
        return testProfileService.getAllProfiles();
    }

    @GetMapping(value = "/testProfile/{id}")
    @ResponseBody
    public TestProfileDto getTestProfileById(@PathVariable Long id) {
        return testProfileService.getTestProfileById(id);
    }

    @PostMapping(value = "/testProfile")
    @ResponseBody
    public TestProfileDto postTestProfile(@Valid @RequestBody TestProfileDto newTestProfileDto) {
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
