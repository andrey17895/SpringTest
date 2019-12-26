package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.profile.RequestDto;

import java.util.List;

public interface IRequestService {
    List<RequestDto> getAllRequests();

    void deleteAll();

    List<RequestDto> getAllRequests(Long testProfileId);

    RequestDto getRequestById(
            Long testProfileId,
            Long requestId
    );

    RequestDto createRequest(
            Long testProfileId,
            RequestDto requestDto
    );

    RequestDto updateRequest(
            Long testProfileId,
            Long requestId,
            RequestDto newRequestDto
    );
}
