package com.example.consumer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class EventDtoDeserializer extends StdDeserializer {
    static ObjectMapper mapper;
    static {
        mapper=new ObjectMapper().registerModule(new JavaTimeModule())
                .configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
    }

    public EventDtoDeserializer(){
        super(EventDtoDeserializer.class);
    }

    protected EventDtoDeserializer(Class vc) {
        super(vc);
    }

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        EventDto eventDto = new EventDto();
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        eventDto.setBefore(mapper.treeToValue(node.get("before"), Event.class));
        eventDto.setAfter(mapper.treeToValue(node.get("after"), Event.class));
        eventDto.setOp(Operation.fromString(node.get("op").asText()));
        return eventDto;
    }
}
