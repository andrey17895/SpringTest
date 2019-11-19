package com.pflb.SpringTest.parser.wrapers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EntryWrapper {
    private RequestWrapper request;
}
