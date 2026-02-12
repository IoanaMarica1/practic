package repo;

import com.fasterxml.jackson.core.type.TypeReference;
import model.Driver;

import java.nio.file.Path;

public class DriverRepo extends AbstractRepoImpl<Driver, Integer> {

    public DriverRepo(Path filePath) {
        super(
                filePath,
                Driver::getId,
                new TypeReference<java.util.List<Driver>>() {}
        );
    }
}