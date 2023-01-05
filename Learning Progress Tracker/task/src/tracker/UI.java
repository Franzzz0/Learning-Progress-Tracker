package tracker;

import java.util.Locale;
import java.util.Scanner;

public class UI {
    private final Scanner scanner;
    private final StudentsDatabase studentsDatabase;
    private final Statistics statistics;

    public UI() {
        this.scanner = new Scanner(System.in).useLocale(Locale.US);
        this.studentsDatabase = new StudentsDatabase();
        this.statistics = new Statistics();
    }

    public void start() {
        System.out.println("Learning Progress Tracker");
        while (true) {
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("exit")) {
                System.out.println("Bye!");
                break;
            }
            if (input.equals("add students")) {
                addStudents();
                continue;
            }
            if (input.equals("list")) {
                studentsDatabase.list();
                continue;
            }
            if (input.equals("add points")) {
                addPoints();
                continue;
            }
            if (input.equals("find")) {
                findStudent();
                continue;
            }
            if (input.equals("statistics")) {
                statisticsMenu();
                continue;
            }
            if (input.equals("notify")) {
                studentsDatabase.notifyStudents();
                continue;
            }
            if (input.equals("back")) {
                System.out.println("Enter 'exit' to exit the program.");
                continue;
            }
            if (input.isBlank()) {
                System.out.println("No input.");
                continue;
            }
            System.out.println("Error: unknown command!");
        }
    }

    private void statisticsMenu() {
        System.out.println("Type the name of a course to see details or 'back' to quit:");
        statistics.printStatistics();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("back")) {
                break;
            }
            statistics.showStatistics(input);
        }
    }

    private void findStudent() {
        System.out.println("Enter an id or 'back' to return:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("back")) {
                break;
            }
            studentsDatabase.find(input);
        }
    }

    private void addPoints() {
        System.out.println("Enter an id and points or 'back' to return:");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("back")) {
                break;
            }
            String[] parts = input.split(" ");
            studentsDatabase.addPoints(parts, statistics);
        }
    }

    private void addStudents() {
        System.out.println("Enter student credentials or 'back' to return:");
        int counter = 0;
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("back")) {
                System.out.printf("Total %d students have been added.%n", counter);
                break;
            }
            String[] parts = input.split(" ");
            if (parts.length < 3) {
                System.out.println("Incorrect credentials.");
                continue;
            }
            String firstName = parts[0];
            StringBuilder lastNameBuilder = new StringBuilder();
            for (int i = 1; i < parts.length - 1; i++) {
                lastNameBuilder.append(parts[i]).append(" ");
            }
            String lastName = lastNameBuilder.toString().trim();
            String email = parts[parts.length - 1];
            if (!firstName.matches("[A-Za-z]+(['-]?[A-Za-z])+")) {
                System.out.println("Incorrect first name.");
                continue;
            }
            if (!lastName.matches("([A-Za-z]+(['-]?[A-Za-z])+ ?)+")) {
                System.out.println("Incorrect last name.");
                continue;
            }
            if (!email.matches("[\\w.]+@\\w+\\.\\w+")) {
                System.out.println("Incorrect email.");
                continue;
            }
            if (studentsDatabase.addStudent(firstName, lastName, email)) {
                counter++;
            }
        }
    }
}
