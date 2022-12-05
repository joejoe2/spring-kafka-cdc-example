package com.example.producer.controller;

import com.example.producer.model.Event;
import com.example.producer.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping(path = "/")
public class EventController {
    @Autowired
    EventRepository eventRepository;
    Random random = new Random();

    @PostMapping
    @Transactional
    public ResponseEntity event(){
        String id = random.nextInt(1000000)+"";
        Event event = eventRepository.findById(id).orElse(new Event(id));
        event.setContent(randomText(random.nextInt(50,255)));
        eventRepository.save(event);
        return ResponseEntity.ok().build();
    }

    private String randomText(int length){
        char[] text=new char[length];
        for (int i=0;i<length;i++){
            text[i]=(char) (random.nextInt(26)+'a');
        }
        return String.valueOf(text);
    }
}
