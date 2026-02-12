package repo;

import com.fasterxml.jackson.core.type.TypeReference;
import model.Event;

import java.nio.file.Path;

public class EventRepo extends AbstractRepoImpl<Event, Integer> {

    public EventRepo(Path filePath) {
        super(
                filePath,
                Event::getId,
                new TypeReference<java.util.List<Event>>() {}
        );
    }
}