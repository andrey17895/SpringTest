package com.pflb.SpringTest.parser.wrapers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pflb.SpringTest.parser.deserializers.ListToMapDeserializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.Map;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestWrapper {
    private String url;

    //    @JsonAlias({"postData"})
//    @JsonDeserialize(using = CustomBodyDeserializer.class)
//    private String body;

    @JsonDeserialize(using = ListToMapDeserializer.class)
    private Map<String, String> headers;

    //    @JsonAlias({"postData"})
//    @JsonDeserialize(using = CustomParamsDeserialiser.class)
    private PostDataWrapper postData;

    private HttpMethod method;

}
