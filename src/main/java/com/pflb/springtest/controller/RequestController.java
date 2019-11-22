package com.pflb.springtest.controller;

import com.pflb.springtest.dto.RequestDto;
import com.pflb.springtest.service.RequestService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestController {

    private RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping(value = "/requests")
    @ResponseBody
    public Iterable<RequestDto> getAllRequests() {
        return requestService.getAllRequests();
    }

    @DeleteMapping(value = "/requests")
    public void deleteAll() {
        requestService.deleteAll();
    }

    @GetMapping(value = "/testProfile/{testProfileId}/requests")
    @ResponseBody
    public Iterable<RequestDto> getAllRequests(@PathVariable Long testProfileId) {
        return requestService.getAllRequests(testProfileId);
    }

    @GetMapping(value = "/testProfile/{testProfileId}/requests/{requestId}")
    @ResponseBody
    public RequestDto getRequestById(
            @PathVariable Long testProfileId,
            @PathVariable Long requestId
    ) {
        return requestService.getRequestById(testProfileId, requestId);
    }

    @PostMapping(value = "/testProfile/{testProfileId}/requests")
    @ResponseBody
    public RequestDto postRequest(
            @PathVariable Long testProfileId,
            @NotNull @RequestBody RequestDto request
    ) {
        return requestService.createRequest(testProfileId, request);
    }

    @PutMapping(value = "/testProfile/{testProfileId}/requests/{requestId}")
    @ResponseBody
    public RequestDto putRequest(
            @PathVariable Long testProfileId,
            @PathVariable Long requestId,
            @NotNull @RequestBody RequestDto newRequestDto
    ) {
        return requestService.updateRequest(testProfileId,requestId, newRequestDto);
    }
}
