package service;

import model.Penalties;
import repo.AbstractRepoImpl;
import repo.AbstractRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class PenaltiesService {
    Path path = Path.of("data/penalties.json");

    AbstractRepository<Penalties, Integer> penaltiessRepo =
            new AbstractRepoImpl<>(
                    path,
                    Penalties::getId,
                    new com.fasterxml.jackson.core.type.TypeReference<List<Penalties>>() {
                    }
            );
    public void countPenalties() {
        long value = penaltiessRepo.count();
        System.out.println("Nr penalties: " + value+"\n");
    }
}