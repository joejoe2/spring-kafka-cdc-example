package com.example.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class EventConsumer {
    ObjectMapper mapper=new ObjectMapper();

    @Autowired
    EventService eventService;

    @KafkaListener(topics = "spring-kafka-cdc-example.public.event", groupId = "consumer", concurrency = "8")
    void consume(String message, Consumer consumer) throws JsonProcessingException {
        EventDto eventDto = mapper.readValue(message, EventDto.class);
        try {
            eventService.consume(eventDto);
            consumer.commitAsync();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
