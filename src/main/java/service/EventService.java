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
    public void countPoints(){
        eventsRepo.findAll().stream()
                .sorted(Comparator.comparing(Event::getId))
                .map(e->{
                    Integer computingPoints = switch(e.getTyp()){
                        case OVERTAKE -> e.getBasePoints()+1;
                        case FASTEST_LAP ->  e.getBasePoints()+2*(e.getLap()%3);
                        case TRACK_LIMITS -> e.getBasePoints()-5;
                        case COLLISION -> e.getBasePoints()-10-e.getLap();
                        case PIT_STOP -> e.getLap()<=10 ? e.getBasePoints()+2 : e.getBasePoints();
                    };
                    return String.format("Event "+e.getId()+"-> raw="+e.getBasePoints()+" ->computed="+computingPoints);
                }).limit(5)
                .forEach(System.out::println);
    }
    public Integer totalPoints(Integer id){
        return eventsRepo.findAll()
                .stream()
                .filter(x->x.getFahrerId().equals(id))
                .mapToInt(e->{
                    return switch(e.getTyp()){
                        case OVERTAKE -> e.getBasePoints()+1;
                        case FASTEST_LAP ->  e.getBasePoints()+2*(e.getLap()%3);
                        case TRACK_LIMITS -> e.getBasePoints()-5;
                        case COLLISION -> e.getBasePoints()-10-e.getLap();
                        case PIT_STOP -> e.getLap()<=10 ? e.getBasePoints()+2 : e.getBasePoints();
                    };
                })
                .sum();

    }

}