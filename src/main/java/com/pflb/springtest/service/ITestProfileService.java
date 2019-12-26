package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.profile.TestProfileDto;

import java.util.List;

public interface ITestProfileService {
    List<TestProfileDto> getAllProfiles();

    TestProfileDto getTestProfileById(Long testProfileId);

    TestProfileDto createTestProfile(TestProfileDto newTestProfileDto);

    TestProfileDto updateTestProfile(TestProfileDto newTestProfileDto, Long testProfileId);

    void deleteAll();
}
