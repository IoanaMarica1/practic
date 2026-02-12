package service;

import model.Driver;
import repo.AbstractRepoImpl;
import repo.AbstractRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class DriverService {
    Path path = Path.of("data/drivers.json");

    AbstractRepository<Driver, Integer> driversRepo =
            new AbstractRepoImpl<>(
                    path,
                    Driver::getId,
                    new com.fasterxml.jackson.core.type.TypeReference<List<Driver>>() {
                    }
            );
    public void countDrivers() {
        long value = driversRepo.count();
        System.out.println("Nr drivers: " + value+"\n");
    }

    public void showDrivers(){
        driversRepo.findAll().stream()
                .map(x->"[#"+x.getId()+"] "+x.getName()+" ("+x.getTeam()+") "+"-"+x.getStatus()+","+" skill="+x.getSkillLevel())
                .forEach(System.out::println);
    }
}