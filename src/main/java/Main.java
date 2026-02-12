import controller.ConsoleController;
import service.DriverService;
import service.PenaltiesService;

import service.EventService;


public class Main {
    public static void main(String[] args) {
        var controller = new ConsoleController(
                new EventService(),
                new DriverService(),
                new PenaltiesService()
        );
        controller.run();
    }
}