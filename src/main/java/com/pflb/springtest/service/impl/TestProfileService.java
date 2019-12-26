package com.pflb.springtest.service.impl;

import com.pflb.springtest.model.dto.profile.TestProfileDto;
import com.pflb.springtest.model.entity.TestProfile;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.model.exception.ResourceNotFoundException;
import com.pflb.springtest.repository.TestProfileRepository;
import com.pflb.springtest.service.ITestProfileService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestProfileService implements ITestProfileService {

    private TestProfileRepository testProfileRepository;

    private ModelMapper modelMapper;

    @Autowired
    public TestProfileService(TestProfileRepository testProfileRepository, ModelMapper modelMapper) {
        this.testProfileRepository = testProfileRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TestProfileDto> getAllProfiles() {

        List<TestProfile> responseEntityList = testProfileRepository.findAll();

        return modelMapper.map(responseEntityList, new TypeToken<List<TestProfileDto>>() {}.getType());
    }

    @Override
    public TestProfileDto getTestProfileById(Long testProfileId) {

        TestProfile testProfile = testProfileRepository.findById(testProfileId)
                .orElseThrow(()-> new ResourceNotFoundException(CustomExceptionType.TEST_PROFILE_NOT_FOUND, testProfileId));

        return modelMapper.map(testProfile, TestProfileDto.class);
    }

    @Override
    public TestProfileDto createTestProfile(TestProfileDto newDto) {

        TestProfile newTestProfile =  modelMapper.map(newDto, TestProfile.class);
        newTestProfile.getRequests().forEach(request -> request.setTestProfile(newTestProfile));

        TestProfile responseEntity = testProfileRepository.save(newTestProfile);

        return modelMapper.map(responseEntity, TestProfileDto.class);
    }

    @Override
    public TestProfileDto updateTestProfile(TestProfileDto newTestProfileDto, Long testProfileId) {

        if (!testProfileRepository.existsById(testProfileId)) {
            throw new ResourceNotFoundException(CustomExceptionType.TEST_PROFILE_NOT_FOUND, testProfileId);
        }

        TestProfile newTestProfile =  modelMapper.map(newTestProfileDto, TestProfile.class);
        newTestProfile.setId(testProfileId);
        newTestProfile.getRequests().forEach(request -> request.setTestProfile(newTestProfile));

        TestProfile responseEntity = testProfileRepository.save(newTestProfile);
;
        return modelMapper.map(responseEntity, TestProfileDto.class);
    }

    @Override
    public void deleteAll() {
        testProfileRepository.deleteAll();
    }

}
