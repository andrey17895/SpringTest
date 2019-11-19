package com.pflb.SpringTest.parser.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class CustomBodyDeserializer extends StdDeserializer<String> {
    public CustomBodyDeserializer(Class<?> vc) {
        super(vc);
    }

    public CustomBodyDeserializer() {
        this(null);
    }

    @Override
    public String deserialize(@NotNull JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String result = null;
        if (node.has("text")) {
            result = node.get("text").asText();
        }
        return result;
    }
}
