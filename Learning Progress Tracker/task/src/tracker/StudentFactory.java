package tracker;

public class StudentFactory {
    private int id;

    public StudentFactory() {
        this.id = 10000;
    }

    public Student getStudent(String firstName, String lastName, String email) {
        return new Student(firstName, lastName, email, getId());
    }

    private int getId() {
        return this.id++;
    }
}
