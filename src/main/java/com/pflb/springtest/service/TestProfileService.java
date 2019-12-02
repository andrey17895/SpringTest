package com.pflb.springtest.service;

import com.pflb.springtest.dto.TestProfileDto;

public interface TestProfileService {
    Iterable<TestProfileDto> getAllProfiles();

    TestProfileDto getTestProfileById(Long testProfileId);

    TestProfileDto createTestProfile(TestProfileDto newTestProfileDto);

    TestProfileDto updateTestProfile(TestProfileDto newTestProfileDto, Long testProfileId);

    void deleteAll();
}
