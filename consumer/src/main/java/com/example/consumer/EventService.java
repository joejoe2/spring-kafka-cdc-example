package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class EventService {
    @Autowired
    EventRepository eventRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(rollbackFor = Exception.class)
    public void consume(EventDto eventDto) throws Exception{
        System.out.println(eventDto);
        Event event = eventRepository
                .findById(eventDto.getAfter()==null?eventDto.getBefore().getId():eventDto.getAfter().getId())
                .orElse(null);

        if (eventDto.getOp()==Operation.Create&&event==null){
            System.out.println("create");
            event = eventDto.getAfter();
            eventRepository.save(event);
        }else if (eventDto.getOp()==Operation.Update&&event!=null
                &&eventDto.getAfter().getVersion().isAfter(event.getVersion())){
            System.out.println("update");
            event.setContent(eventDto.getAfter().getContent());
            event.setVersion(eventDto.getAfter().getVersion());
            eventRepository.save(event);
        }else if (eventDto.getOp()==Operation.Delete&&event!=null
                &&eventDto.getAfter().getVersion().isAfter(event.getVersion())){
            System.out.println("delete");
            eventRepository.delete(event);
        }else throw new Exception("this event should be discarded !");
    }
}
