package service;

import model.Driver;
import model.Status;
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
                .map(x->"["+x.getId()+"] "+x.getName()+" ("+x.getTeam()+") "+"-"+x.getStatus()+","+" skill="+x.getSkillLevel())
                .forEach(System.out::println);
    }

    public void filterDrivers(String team){
        driversRepo.findAll().stream()
                .filter(x->x.getTeam().equals(team) && x.getStatus().equals(Status.ACTIVE))
                .map(x->"["+x.getId()+"] "+x.getName()+" ("+x.getTeam()+") "+"-"+x.getStatus()+","+" skill="+x.getSkillLevel())
                .forEach(System.out::println);
    }
    public void sortDrivers(){
        driversRepo.findAll().stream()
                .sorted(Comparator.comparing(Driver::getSkillLevel).reversed().thenComparing(Driver::getName))
                .map(x->"["+x.getId()+"] "+x.getName()+" ("+x.getTeam()+") "+"-"+x.getStatus()+","+" skill="+x.getSkillLevel())
                .forEach(System.out::println);
    }
    public void fileSorted(){
        try(PrintWriter writer = new PrintWriter(new FileWriter("drivers_sorted.txt") )) {
            driversRepo.findAll().stream()
                    .sorted(Comparator.comparing(Driver::getSkillLevel).reversed().thenComparing(Driver::getName))
                    .map(x->"["+x.getId()+"] "+x.getName()+" ("+x.getTeam()+") "+"-"+x.getStatus()+","+" skill="+x.getSkillLevel())
                    .forEach(writer::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void computingPoints(PenaltiesService penaltiesService, EventService eventService){
        System.out.println("Top 5 drivers: "+"\n");
        record Ranking(String name, String team, Integer score){

        }

        driversRepo.findAll().stream()
                .map(driver->{
                    Integer totalP = penaltiesService.totalPenalties(driver.getId());
                    Integer totalC = eventService.totalPoints(driver.getId());
                    Integer score = totalC-totalP;
                    return new Ranking(driver.getName(), driver.getTeam(), score);
                })
                 .sorted(Comparator.comparing(Ranking::score).reversed().thenComparing(Ranking::name))
                .limit(5)
                .forEach(e->System.out.printf("%s (%s) -> %d%n",e.name(),e.team(),e.score()));

    }
    public void top1(PenaltiesService penaltiesService, EventService eventService){
        System.out.println("Winning team: ");
        record Ranking(String name, String team, Integer score){

        }

        driversRepo.findAll().stream()
                .map(driver->{
                    Integer totalP = penaltiesService.totalPenalties(driver.getId());
                    Integer totalC = eventService.totalPoints(driver.getId());
                    Integer score = totalC-totalP;
                    return new Ranking(driver.getName(), driver.getTeam(), score);
                })
                .sorted(Comparator.comparing(Ranking::score).reversed().thenComparing(Ranking::name))
                .limit(1)
                .forEach(e->System.out.printf("%s%n",e.team()));

    }

}