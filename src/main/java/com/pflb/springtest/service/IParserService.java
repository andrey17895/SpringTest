package com.pflb.springtest.service;

import com.pflb.springtest.model.dto.har.HarDto;
import org.springframework.web.multipart.MultipartFile;

public interface IParserService {
    HarDto parse(MultipartFile file);
}
