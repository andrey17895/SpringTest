package com.pflb.SpringTest.parser.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ListToMapDeserializer extends StdDeserializer<Map<String, String>> {

    public ListToMapDeserializer() {
        this(null);
    }

    public ListToMapDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Map<String, String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ArrayNode node = jsonParser.getCodec().readTree(jsonParser);
        Map<String, String> result = new HashMap<>();
        for (JsonNode parm: node) {
            String key = parm.get("name").asText();
            String value = parm.get("value").asText();
            result.put(key, value);
        }
        return result;
    }
}
