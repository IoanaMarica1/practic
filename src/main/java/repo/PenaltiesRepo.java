package repo;

import com.fasterxml.jackson.core.type.TypeReference;
import model.Penalties;

import java.nio.file.Path;

public class PenaltiesRepo extends AbstractRepoImpl<Penalties, Integer> {

    public PenaltiesRepo(Path filePath) {
        super(
                filePath,
                Penalties::getId,
                new TypeReference<java.util.List<Penalties>>() {}
        );
    }
}