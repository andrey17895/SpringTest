package com.pflb.SpringTest.parser.wrapers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pflb.SpringTest.parser.deserializers.ListToMapDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PostDataWrapper {

    private String text;

    @JsonDeserialize(using = ListToMapDeserializer.class)
    private Map<String, String> params;
}
