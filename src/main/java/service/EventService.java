package service;

import model.Event;
import repo.AbstractRepoImpl;
import repo.AbstractRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class EventService {
    Path path = Path.of("data/events.json");

    AbstractRepository<Event, Integer> eventsRepo =
            new AbstractRepoImpl<>(
                    path,
                    Event::getId,
                    new com.fasterxml.jackson.core.type.TypeReference<List<Event>>() {
                    }
            );
    public void countEvents() {
        long value = eventsRepo.count();
        System.out.println("Nr events: " + value+"\n");
    }
    public void countTyp(){
        eventsRepo.findAll().stream()
                .collect(Collectors.groupingBy(Event::getTyp,Collectors.counting()))
                .entrySet().stream()
                .map(e->e.getKey()+"->"+e.getValue())
                .forEach(System.out::println);
    }
}