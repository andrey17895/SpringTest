package com.pflb.SpringTest.rest;

import com.pflb.SpringTest.data.entities.Request;
import com.pflb.SpringTest.data.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {

    @Autowired
    private RequestRepository requestRepository;

    @GetMapping(value = "/requests")
    @ResponseBody
    public Iterable<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @DeleteMapping(value = "/requests")
    public void deleteAll() {
        requestRepository.deleteAll();
    }
}
