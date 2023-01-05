package tracker;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Statistics {
    private final List<Course> courses;

    public Statistics() {
        this.courses = new ArrayList<>();
        for (Courses course : Courses.values()) {
            this.courses.add(new Course(course));
        }
    }

    public void updatePoints(Student student, int[] points) {
        for (Course course : courses) {
            if (points[course.getCourse().ordinal()] > 0) {
                course.addPoints(student, points[course.getCourse().ordinal()]);
            }
        }
    }

    public void printStatistics() {
        String[] data = getStatisticsData();
        System.out.printf("Most popular: %s%n", data[0]);
        System.out.printf("Least popular: %s%n", data[1]);
        System.out.printf("Highest activity: %s%n", data[2]);
        System.out.printf("Lowest activity: %s%n", data[3]);
        System.out.printf("Easiest course: %s%n", data[4]);
        System.out.printf("Hardest course: %s%n", data[5]);
    }

    public void showStatistics(String input) {
        Course course = findCourse(input);
        if (course != null) {
            course.printStatistics();
        } else {
            System.out.println("Unknown course.");
        }
    }

    private Course findCourse(String input) {
        for (Course course : courses) {
            if (course.getCourse().getName().equalsIgnoreCase(input)) {
                return course;
            }
        }
        return null;
    }

    private String[] getStatisticsData() {
        String[] data = new String[] {"n/a", "n/a", "n/a", "n/a", "n/a", "n/a"};

        this.courses.sort(Comparator.comparing(Course::getEnrollments));
        int maxEnrollments = this.courses.get(3).getEnrollments();
        if (maxEnrollments != 0) {
            data[0] = courses.get(3).getCourse().getName();
            for (int i = 2; i >= 0; i--) {
                if (courses.get(i).getEnrollments() == maxEnrollments) {
                    data[0] = data[0] + ", " + courses.get(i).getCourse().getName();
                }
            }
        }
        int minEnrollments = courses.get(0).getEnrollments();
        if (minEnrollments != maxEnrollments) {
            data[1] = this.courses.get(0).getCourse().getName();
            for (int i = 1; i <= 3; i++) {
                if (courses.get(i).getEnrollments() == minEnrollments) {
                    data[1] = data[1] + ", " + courses.get(i).getCourse().getName();
                }
            }
        }

        this.courses.sort(Comparator.comparing(Course::getNumberOfTasks));
        int maxTasks = this.courses.get(3).getNumberOfTasks();
        if (maxTasks != 0) {
            data[2] = courses.get(3).getCourse().getName();
            for (int i = 2; i >= 0; i--) {
                if (courses.get(i).getNumberOfTasks() == maxTasks) {
                    data[2] = data[2] + ", " + courses.get(i).getCourse().getName();
                }
            }
        }
        int minTasks = courses.get(0).getNumberOfTasks();
        if (minTasks != maxTasks) {
            data[3] = this.courses.get(0).getCourse().getName();
            for (int i = 1; i <= 3; i++) {
                if (courses.get(i).getNumberOfTasks() == minTasks) {
                    data[3] = data[3] + ", " + courses.get(i).getCourse().getName();
                }
            }
        }

        this.courses.sort(Comparator.comparing(Course::getAvgAssignment));
        double maxAvg = this.courses.get(3).getAvgAssignment();
        if (maxAvg != 0) {
            data[4] = courses.get(3).getCourse().getName();
            for (int i = 2; i >= 0; i--) {
                if (courses.get(i).getAvgAssignment() == maxAvg) {
                    data[4] = data[4] + ", " + courses.get(i).getCourse().getName();
                }
            }
        }
        double minAvg = courses.get(0).getAvgAssignment();
        if (minAvg != maxAvg) {
            data[5] = this.courses.get(0).getCourse().getName();
            for (int i = 1; i <= 3; i++) {
                if (courses.get(i).getAvgAssignment() == minAvg) {
                    data[5] = data[5] + ", " + courses.get(i).getCourse().getName();
                }
            }
        }

        return data;
    }
}
