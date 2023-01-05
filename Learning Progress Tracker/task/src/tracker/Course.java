package tracker;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Course {
    private final Courses course;
    private final List<Student> enrollments = new ArrayList<>();
    private final List<Integer> assignments = new ArrayList<>();

    public Course(Courses course) {
        this.course = course;
    }

    public Courses getCourse() {
        return this.course;
    }

    public void addPoints(Student student, int points) {
        if (!this.enrollments.contains(student)) {
            this.enrollments.add(student);
        }
        this.assignments.add(points);
    }

    public int getEnrollments() {
        return this.enrollments.size();
    }

    public double getAvgAssignment() {
        if (!assignments.isEmpty()){
            int sum = 0;
            for (int points : assignments) {
                sum += points;
            }
            return 1.0 * sum / assignments.size();
        } else return 0.0;
    }

    public int getNumberOfTasks() {
        return this.assignments.size();
    }

    public void printStatistics() {
        int courseID = this.course.ordinal();
        int maxPoints = this.course.getMaxPoints();
        Comparator<Student> comparator = (s1, s2) -> s2.getPoints(courseID) - s1.getPoints(courseID);

        System.out.println(this.course.getName());
        System.out.println("id     points\tcompleted");

        this.enrollments.sort(comparator.thenComparing(Student::getId));
        for (Student student : enrollments) {
            int points = student.getPoints(courseID);
            System.out.printf("%d  %d\t\t%s\n", student.getId(), points, getCompletePercent(maxPoints, points));
        }
    }

    private static String getCompletePercent(int maxPoints, int points) {
        int percent = (int) (10 * new BigDecimal(1.0 * points / maxPoints * 100)
                            .setScale(1, RoundingMode.HALF_UP).doubleValue());
        return percent / 10 + "." + percent % 10 + "%";
    }
}
