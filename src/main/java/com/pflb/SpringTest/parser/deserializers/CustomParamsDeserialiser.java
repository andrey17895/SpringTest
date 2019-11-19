package com.pflb.SpringTest.parser.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomParamsDeserialiser extends StdDeserializer<Map<String, String>> {
    public CustomParamsDeserialiser() { this(null); }

    public CustomParamsDeserialiser(Class<?> vc) {
        super(vc);
    }

    @Override
    public Map<String, String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        Map<String, String> result = new HashMap<>();
        if (node.has("params")) {
            JsonNode arrayNode = node.get("params");
            for (JsonNode parm : arrayNode) {
                if (parm.has("name") && parm.has("value")) {
                    String key = parm.get("name").asText();
                    String value = parm.get("value").asText();
                    result.put(key, value);
                }
            }
        }
        return result;
    }
}
