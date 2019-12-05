package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.har.HarDto;

public interface IListenerService {
    void process(HarDto message);
}
