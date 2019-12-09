package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.har.HarDto;

import java.util.Optional;

public interface IHarParserService {
    Optional<HarDto> parse(String message);
}
