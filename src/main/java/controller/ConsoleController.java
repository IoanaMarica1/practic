package controller;
import service.DriverService;
import service.EventService;
import service.PenaltiesService;


import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleController {

    private final EventService eventService;
    private final DriverService driverService;
    private final PenaltiesService penaltiesService;

    private final Scanner scanner = new Scanner(System.in);

    // folosim LinkedHashMap ca să păstrăm ordinea meniului
    private final Map<Integer, MenuItem> menu = new LinkedHashMap<>();

    public ConsoleController(EventService eventService, DriverService driverService, PenaltiesService penaltiesService) {
        this.eventService = eventService;
        this.driverService = driverService;
        this.penaltiesService = penaltiesService;

        registerMenu();
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = readInt("Choose option: ");

            if (choice == 0) {
                System.out.println("Bye!");
                return;
            }

            MenuItem item = menu.get(choice);
            if (item == null) {
                System.out.println("Invalid option.");
                continue;
            }

            System.out.println("--------------------------------------------------------------");
            try {
                item.action.run();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                // debug:
                // e.printStackTrace();
            }
            System.out.println("--------------------------------------------------------------");
        }
    }

    // -------------------- MENU REGISTRATION --------------------

    private void registerMenu() {

        // 1
        menu.put(1, new MenuItem("Counts + print drivers", this::option1));

        // 2
        menu.put(2, new MenuItem("Filter Drivers", this::option2));

        // 3
        menu.put(3, new MenuItem("Sort tributes", this::option3));

        // 4
        menu.put(4, new MenuItem("Sort tributes in file", this::option4));

        // 5
        menu.put(5, new MenuItem("Calculate points", this::option5));

        // 6
        menu.put(6, new MenuItem("Ranking Top 5 by score", this::option6));

        // 7
        menu.put(7, new MenuItem("Count Arena", this::option7));
    }

    private void printMenu() {
        System.out.println();
        System.out.println("============== FORMULA 1 CONSOLE ==============");
        menu.forEach((k, v) -> System.out.printf("%d. %s%n", k, v.label));
        System.out.println("0. Exit");
        System.out.println("==================================================");
    }

    // -------------------- OPTIONS (1..7) --------------------

    /**
     * 1)
     */
    private void option1() {
         driverService.countDrivers();
         eventService.countEvents();
         penaltiesService.countPenalties();
         driverService.showDrivers();
    }

    /**
     * 2)
     */
    private void option2() {
        String d = readNonEmpty("District number: ");
        driverService.filterDrivers(d);

    }

    /**
     * 3)
     */
    private void option3() {

    }

    /**
     * 4)
     */
    private void option4() {


    }

    /**
     * 5)
     */
    private void option5() {


    }
    //
//    /**
//     * 6)
//     */
    private void option6() {
        ;
    }
    //
//    /**
//     * 7)
//     */
    private void option7() {



    }

    // -------------------- INPUT HELPERS --------------------

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }


    private String readNonEmpty(String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = scanner.nextLine();
            if (s != null && !s.trim().isEmpty()) return s.trim();
            System.out.println("Value must not be empty.");
        }
    }

    // -------------------- SMALL MENU ITEM MODEL --------------------

    private static class MenuItem {
        final String label;
        final Runnable action;

        MenuItem(String label, Runnable action) {
            this.label = label;
            this.action = action;
        }
    }
}