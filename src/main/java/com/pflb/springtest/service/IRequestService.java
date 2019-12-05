package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.profile.RequestDto;

import java.util.Collection;

public interface IRequestService {
    Collection<RequestDto> getAllRequests();

    void deleteAll();

    Collection<RequestDto> getAllRequests(Long testProfileId);

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
