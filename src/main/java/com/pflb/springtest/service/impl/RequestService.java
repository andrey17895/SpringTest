package com.pflb.springtest.service.impl;

import com.pflb.springtest.model.dto.profile.RequestDto;
import com.pflb.springtest.model.entity.Request;
import com.pflb.springtest.model.entity.TestProfile;
import com.pflb.springtest.model.exception.CustomExceptionType;
import com.pflb.springtest.model.exception.ResourceNotFoundException;
import com.pflb.springtest.repository.RequestRepository;
import com.pflb.springtest.repository.TestProfileRepository;
import com.pflb.springtest.service.IRequestService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService implements IRequestService {

    private RequestRepository requestRepository;

    private TestProfileRepository testProfileRepository;

    private ModelMapper modelMapper;

    @Autowired
    public RequestService(RequestRepository requestRepository, TestProfileRepository testProfileRepository, ModelMapper modelMapper) {
        this.requestRepository = requestRepository;
        this.testProfileRepository = testProfileRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<RequestDto> getAllRequests() {

        List<Request> requestEntityIterable = requestRepository.findAll();

        return modelMapper.map(requestEntityIterable, new TypeToken<List<RequestDto>>() {}.getType());
    }

    @Override
    public void deleteAll() {
        requestRepository.deleteAll();
    }

    @Override
    public List<RequestDto> getAllRequests(Long testProfileId) {

        if (!testProfileRepository.existsById(testProfileId)) {
            throw new ResourceNotFoundException(CustomExceptionType.TEST_PROFILE_NOT_FOUND, testProfileId);
        }

        List<Request> requestEntityIterable = requestRepository.findByTestProfileId(testProfileId);

        return modelMapper.map(requestEntityIterable, new TypeToken<List<RequestDto>>() {}.getType());
    }

    @Override
    public RequestDto getRequestById(
            Long testProfileId,
            Long requestId
    ) {
        if (!testProfileRepository.existsById(testProfileId)) {
            throw new ResourceNotFoundException(CustomExceptionType.TEST_PROFILE_NOT_FOUND, testProfileId);
        }

        Request request = requestRepository.findByIdAndTestProfileId(requestId, testProfileId)
                .orElseThrow(() -> new ResourceNotFoundException(CustomExceptionType.REQUEST_NOT_FOUND, requestId));

        return modelMapper.map(request, RequestDto.class);
    }

    @Override
    public RequestDto createRequest(
            Long testProfileId,
            RequestDto requestDto
    ) {
        TestProfile testProfile = testProfileRepository.findById(testProfileId)
                .orElseThrow(() -> new ResourceNotFoundException(CustomExceptionType.TEST_PROFILE_NOT_FOUND, testProfileId));

        Request request = modelMapper.map(requestDto, Request.class);
        request.setTestProfile(testProfile);

        return modelMapper.map(requestRepository.save(request), RequestDto.class);
    }

    @Override
    public RequestDto updateRequest(
            Long testProfileId,
            Long requestId,
            RequestDto newRequestDto
    ) {
        TestProfile testProfile = testProfileRepository.findById(testProfileId)
                .orElseThrow(() -> new ResourceNotFoundException(CustomExceptionType.TEST_PROFILE_NOT_FOUND, testProfileId));

        if (!requestRepository.existsById(requestId)) {
            throw new ResourceNotFoundException(CustomExceptionType.REQUEST_NOT_FOUND, requestId);
        }

        Request newRequest = modelMapper.map(newRequestDto, Request.class);
        newRequest.setTestProfile(testProfile);
        newRequest.setId(requestId);

        return modelMapper.map(requestRepository.save(newRequest), RequestDto.class);
    }

}
