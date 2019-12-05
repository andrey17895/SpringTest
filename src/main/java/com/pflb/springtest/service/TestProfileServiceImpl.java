package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.profile.TestProfileDto;
import com.pflb.springtest.model.entity.TestProfile;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.model.exception.ResourceNotFoundException;
import com.pflb.springtest.repository.TestProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TestProfileServiceImpl implements ITestProfileService {

    private TestProfileRepository testProfileRepository;

    private ModelMapper modelMapper;

    @Autowired
    public TestProfileServiceImpl(TestProfileRepository testProfileRepository, ModelMapper modelMapper) {
        this.testProfileRepository = testProfileRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public Collection<TestProfileDto> getAllProfiles() {
        Iterable<TestProfile> responseEntityList = testProfileRepository.findAll();
        return StreamSupport.stream(responseEntityList.spliterator(), false)
                .map(testProfile -> modelMapper.map(testProfile, TestProfileDto.class)
                ).collect(Collectors.toList());
    }

    @Override
    public TestProfileDto getTestProfileById(Long testProfileId) {
        TestProfile testProfile = testProfileRepository.findById(testProfileId)
                .orElseThrow(()-> new ResourceNotFoundException(CustomExceptionType.TEST_PROFILE_NOT_FOUND, testProfileId));
        return modelMapper.map(testProfile, TestProfileDto.class);
    }

    @Override
    public TestProfileDto createTestProfile(TestProfileDto newTestProfileDto) {
        TestProfile newTestProfile =  modelMapper.map(newTestProfileDto, TestProfile.class);
        TestProfile responseEntity = testProfileRepository.save(newTestProfile);
        return modelMapper.map(responseEntity, TestProfileDto.class);
    }

    @Override
    public TestProfileDto updateTestProfile(TestProfileDto newTestProfileDto, Long testProfileId) {
        TestProfile newTestProfile =  modelMapper.map(newTestProfileDto, TestProfile.class);

        if (!testProfileRepository.existsById(testProfileId)) {
            throw new ResourceNotFoundException(CustomExceptionType.TEST_PROFILE_NOT_FOUND, testProfileId);
        }
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
