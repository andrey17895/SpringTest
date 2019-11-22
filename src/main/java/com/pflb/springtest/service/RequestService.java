package com.pflb.springtest.service;

import com.pflb.springtest.dto.RequestDto;
import com.pflb.springtest.entity.RequestEntity;
import com.pflb.springtest.entity.TestProfileEntity;
import com.pflb.springtest.model.CustomExceptionType;
import com.pflb.springtest.model.ResourceNotFoundException;
import com.pflb.springtest.repository.RequestRepository;
import com.pflb.springtest.repository.TestProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RequestService {

    private RequestRepository requestRepository;

    private TestProfileRepository testProfileRepository;

    private ModelMapper modelMapper;

    @Autowired
    public RequestService(RequestRepository requestRepository, TestProfileRepository testProfileRepository, ModelMapper modelMapper) {
        this.requestRepository = requestRepository;
        this.testProfileRepository = testProfileRepository;
        this.modelMapper = modelMapper;
    }


    public Iterable<RequestDto> getAllRequests() {
        Iterable<RequestEntity> requestEntityIterable = requestRepository.findAll();
        return StreamSupport.stream(requestEntityIterable.spliterator(), false)
                .map(entity -> modelMapper.map(entity, RequestDto.class))
                .collect(Collectors.toList());
    }

    public void deleteAll() {
        requestRepository.deleteAll();
    }

    public Iterable<RequestDto> getAllRequests(Long testProfileId) {
        Iterable<RequestEntity> requestEntityIterable = requestRepository.findByTestProfileId(testProfileId);
        return StreamSupport.stream(requestEntityIterable.spliterator(), false)
                .map(entity -> modelMapper.map(entity, RequestDto.class))
                .collect(Collectors.toList());
    }

    public RequestDto getRequestById(
            Long testProfileId,
            Long requestId
    ) {
        if (!testProfileRepository.existsById(testProfileId)) {
            throw new ResourceNotFoundException(CustomExceptionType.TEST_PROFILE_NOT_FOUND, testProfileId);
        }
        RequestEntity requestEntity = requestRepository.findByIdAndTestProfileId(requestId, testProfileId)
                .orElseThrow(() -> new ResourceNotFoundException(CustomExceptionType.REQUEST_NOT_FOUND, requestId));

        return modelMapper.map(requestEntity, RequestDto.class);
    }

    public RequestDto createRequest(
            Long testProfileId,
            RequestDto requestDto
    ) {
        TestProfileEntity testProfile = testProfileRepository.findById(testProfileId)
                .orElseThrow(() -> new ResourceNotFoundException(CustomExceptionType.TEST_PROFILE_NOT_FOUND, testProfileId));
        RequestEntity requestEntity = modelMapper.map(requestDto, RequestEntity.class);
        requestEntity.setTestProfile(testProfile);
        return modelMapper.map(requestRepository.save(requestEntity), RequestDto.class);
    }

    public RequestDto updateRequest(
             Long testProfileId,
             Long requestId,
             RequestDto newRequestDto
    ) {
        TestProfileEntity testProfileEntity = testProfileRepository.findById(testProfileId)
                .orElseThrow(() -> new ResourceNotFoundException(CustomExceptionType.TEST_PROFILE_NOT_FOUND, testProfileId));
        RequestEntity newRequestEntity = modelMapper.map(newRequestDto, RequestEntity.class);
        newRequestEntity.setTestProfile(testProfileEntity);
        if (!requestRepository.existsById(requestId)) {
            throw new ResourceNotFoundException(CustomExceptionType.REQUEST_NOT_FOUND, requestId);
        }
        newRequestEntity.setId(requestId);
        return modelMapper.map(requestRepository.save(newRequestEntity), RequestDto.class);
    }
}
