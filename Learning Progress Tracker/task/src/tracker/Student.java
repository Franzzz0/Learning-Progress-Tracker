package tracker;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final int id;
    private final int[] points;
    private final boolean[] coursesCompleted;
    public Student(String firstName, String lastName, String email, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.id = id;
        this.points = new int[4];
        this.coursesCompleted = new boolean[] {false, false, false, false};
    }

    public int getId() {
        return id;
    }

    public int getPoints(int course) {
        return points[course];
    }

    public void updatePoints(int[] points) {
        this.points[Courses.JAVA.ordinal()] += points[Courses.JAVA.ordinal()];
        this.points[Courses.DSA.ordinal()] += points[Courses.DSA.ordinal()];
        this.points[Courses.DATABASES.ordinal()] += points[Courses.DATABASES.ordinal()];
        this.points[Courses.SPRING.ordinal()] += points[Courses.SPRING.ordinal()];
    }

    public boolean notifyCompleted() {
        boolean wasNotified = false;
        for (int i = 0; i < points.length; i++) {
            if (!coursesCompleted[i] && points[i] >= Courses.values()[i].getMaxPoints()) {
                coursesCompleted[i] = true;
                sendNotification(i);
                wasNotified = true;
            }
        }
        return wasNotified;
    }

    private void sendNotification(int i) {
        System.out.printf("To: %s%n", this.email);
        System.out.println("Re: Your Learning Progress");
        System.out.printf("Hello, %s %s! You have accomplished our %s course!%n",
                this.firstName,
                this.lastName,
                Courses.values()[i].getName());
    }

    @Override
    public String toString() {
        return String.format("%d points: Java=%d; DSA=%d; Databases=%d; Spring=%d", id,
                points[Courses.JAVA.ordinal()],
                points[Courses.DSA.ordinal()],
                points[Courses.DATABASES.ordinal()],
                points[Courses.SPRING.ordinal()]);
    }
}
