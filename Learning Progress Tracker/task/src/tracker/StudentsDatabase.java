package tracker;

import java.util.*;

public class StudentsDatabase {
    private final Map<Integer, Student> studentsMap;
    private final Set<String> emailSet;
    private final StudentFactory studentFactory;

    public StudentsDatabase() {
        this.studentFactory = new StudentFactory();
        this.studentsMap = new LinkedHashMap<>();
        this.emailSet = new HashSet<>();
    }

    public void list() {
        if (studentsMap.isEmpty()) {
            System.out.println("No students found");
        } else {
            System.out.println("Students:");
            for (Integer id : studentsMap.keySet()) {
                System.out.println(id);
            }
        }
    }

    public boolean addStudent(String firstName, String lastName, String email) {
        if (emailSet.contains(email)) {
            System.out.println("This email is already taken.");
            return false;
        } else {
            Student student = studentFactory.getStudent(firstName, lastName, email);
            studentsMap.put(student.getId(), student);
            emailSet.add(email);
            System.out.println("The student has been added.");
            return true;
        }
    }

    public void addPoints(String[] input, Statistics statistics) {
        if (input.length == 5) {
            int id;
            try {
                id = Integer.parseInt(input[0]);
                if (!studentsMap.containsKey(id)) {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.printf("No student is found for id=%s.%n", input[0]);
                return;
            }

            int[] points= new int[4];
            for (int i = 0; i < points.length; i++) {
                try {
                    points[i] = Integer.parseInt(input[i + 1]);
                    if (points[i] < 0) {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    System.out.println("Incorrect points format.");
                    return;
                }
            }
            studentsMap.get(id).updatePoints(points);
            statistics.updatePoints(studentsMap.get(id), points);
            System.out.println("Points updated.");
        } else {
            System.out.println("Incorrect points format.");
        }
    }

    public void find(String input) {
        int id = Integer.parseInt(input);
        if (studentsMap.containsKey(id)) {
            System.out.println(studentsMap.get(id));
        } else {
            System.out.printf("No student is found for id=%s%n", id);
        }
    }

    public void notifyStudents() {
        int counter = 0;
        for (Student student : studentsMap.values()) {
            if (student.notifyCompleted()) {
                counter++;
            }
        }
        System.out.printf("Total %d students have been notified.%n", counter);
    }
}
