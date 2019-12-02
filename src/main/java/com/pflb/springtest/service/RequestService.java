package com.pflb.springtest.service;

import com.pflb.springtest.dto.RequestDto;

public interface RequestService {
    Iterable<RequestDto> getAllRequests();

    void deleteAll();

    Iterable<RequestDto> getAllRequests(Long testProfileId);

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
