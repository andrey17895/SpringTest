package com.pflb.springtest.service;

import com.pflb.springtest.dto.TestProfileDto;
import com.pflb.springtest.entity.TestProfileEntity;
import com.pflb.springtest.model.CustomExceptionType;
import com.pflb.springtest.model.ResourceNotFoundException;
import com.pflb.springtest.repository.TestProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TestProfileServiceImpl implements TestProfileService {

    private TestProfileRepository testProfileRepository;

    private ModelMapper modelMapper;

    @Autowired
    public TestProfileServiceImpl(TestProfileRepository testProfileRepository, ModelMapper modelMapper) {
        this.testProfileRepository = testProfileRepository;
        this.modelMapper = modelMapper;
    }



    @Override
    public Iterable<TestProfileDto> getAllProfiles() {
        Iterable<TestProfileEntity> responseEntityList = testProfileRepository.findAll();
        return StreamSupport.stream(responseEntityList.spliterator(), false)
                .map(testProfile -> modelMapper.map(testProfile, TestProfileDto.class)
                ).collect(Collectors.toList());
    }

    @Override
    public TestProfileDto getTestProfileById(Long testProfileId) {
        TestProfileEntity testProfileEntity = testProfileRepository.findById(testProfileId)
                .orElseThrow(()-> new ResourceNotFoundException(CustomExceptionType.TEST_PROFILE_NOT_FOUND, testProfileId));
        return modelMapper.map(testProfileEntity, TestProfileDto.class);
    }

    @Override
    public TestProfileDto createTestProfile(TestProfileDto newTestProfileDto) {
        TestProfileEntity newTestProfileEntity =  modelMapper.map(newTestProfileDto, TestProfileEntity.class);
        TestProfileEntity responseEntity = testProfileRepository.save(newTestProfileEntity);
        return modelMapper.map(responseEntity, TestProfileDto.class);
    }

    @Override
    public TestProfileDto updateTestProfile(TestProfileDto newTestProfileDto, Long testProfileId) {
        TestProfileEntity newTestProfileEntity =  modelMapper.map(newTestProfileDto, TestProfileEntity.class);

        if (!testProfileRepository.existsById(testProfileId)) {
            throw new ResourceNotFoundException(CustomExceptionType.TEST_PROFILE_NOT_FOUND, testProfileId);
        }
        newTestProfileEntity.setId(testProfileId);
        newTestProfileEntity.getRequests().forEach(request -> request.setTestProfile(newTestProfileEntity));
        TestProfileEntity responseEntity = testProfileRepository.save(newTestProfileEntity);
;
        return modelMapper.map(responseEntity, TestProfileDto.class);
    }

    @Override
    public void deleteAll() {
        testProfileRepository.deleteAll();
    }

}
