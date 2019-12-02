package com.pflb.springtest.service;

import com.pflb.springtest.dto.HarDto;

public interface ListenerService {
    void process(HarDto message);
}
