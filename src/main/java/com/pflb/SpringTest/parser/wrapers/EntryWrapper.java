package com.pflb.SpringTest.parser.wrapers;

import com.pflb.SpringTest.data.entities.Request;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class EntryWrapper {
    private Request request;
}
