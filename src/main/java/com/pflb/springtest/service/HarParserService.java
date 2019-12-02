package com.pflb.springtest.service;

import com.pflb.springtest.dto.HarDto;

import java.util.Optional;

public interface HarParserService {
    Optional<HarDto> parse(String message);
}
