package com.pflb.SpringTest.rest;

import com.pflb.SpringTest.data.entities.Request;
import com.pflb.SpringTest.data.entities.TestProfile;
import com.pflb.SpringTest.data.repositories.RequestRepository;
import com.pflb.SpringTest.data.repositories.TestProfileRepository;
import com.pflb.SpringTest.rest.exceptions.ResourceNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RequestController {

    private RequestRepository requestRepository;

    private TestProfileRepository testProfileRepository;

    @Autowired
    public RequestController(RequestRepository requestRepository, TestProfileRepository testProfileRepository) {
        this.requestRepository = requestRepository;
        this.testProfileRepository = testProfileRepository;
    }

    @GetMapping(value = "/requests")
    @ResponseBody
    public Iterable<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @DeleteMapping(value = "/requests")
    public void deleteAll() {
        requestRepository.deleteAll();
    }

    @GetMapping(value = "/testProfile/{testProfileId}/requests")
    @ResponseBody
    public Iterable<Request> getAllRequests(@PathVariable Long testProfileId) {
        return requestRepository.findByTestProfileId(testProfileId);
    }

    @GetMapping(value = "/testProfile/{testProfileId}/requests/{requestId}")
    @ResponseBody
    public Request getRequestById(
            @PathVariable Long testProfileId,
            @PathVariable Long requestId
    ) {
        if (!testProfileRepository.existsById(testProfileId)) {
            throw new ResourceNotFoundException("Test Profile not found: id=" + testProfileId);
        }
        return requestRepository.findByIdAndTestProfileId(requestId, testProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found: id=" + requestId));
    }

    @PostMapping(value = "/testProfile/{testProfileId}/requests")
    @ResponseBody
    public Iterable<Request> postAllRequests(
            @PathVariable Long testProfileId,
            @NotNull @RequestBody Iterable<Request> requestsList
    ) {
        TestProfile testProfile = testProfileRepository.findById(testProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("Test Profile not found: id=" + testProfileId));
        requestsList.forEach(x -> x.setId(null));
        requestsList.forEach(x -> x.setTestProfile(testProfile));
        return requestRepository.saveAll(requestsList);
    }

    @PutMapping(value = "/testProfile/{testProfileId}/requests/{requestId}")
    @ResponseBody
    public Request putRequest(
            @PathVariable Long testProfileId,
            @PathVariable Long requestId,
            @NotNull @RequestBody Request newRequest
    ) {
        TestProfile testProfile = testProfileRepository.findById(testProfileId)
                .orElseThrow(() -> new ResourceNotFoundException("Test Profile not found: id=" + testProfileId));
        newRequest.setTestProfile(testProfile);
        if (!requestRepository.existsById(requestId)) {
            throw new ResourceNotFoundException("Request not found: id=" + requestId);
        }
        newRequest.setId(requestId);
        return requestRepository.save(newRequest);
    }
}
