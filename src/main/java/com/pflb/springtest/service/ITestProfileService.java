package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.profile.TestProfileDto;

import java.util.Collection;

public interface ITestProfileService {
    Collection<TestProfileDto> getAllProfiles();

    TestProfileDto getTestProfileById(Long testProfileId);

    TestProfileDto createTestProfile(TestProfileDto newTestProfileDto);

    TestProfileDto updateTestProfile(TestProfileDto newTestProfileDto, Long testProfileId);

    void deleteAll();
}
