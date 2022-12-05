package com.example.consumer;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonDeserialize(using = EventDtoDeserializer.class)
public class EventDto {
    Event before;
    Event after;
    Operation op;
}
