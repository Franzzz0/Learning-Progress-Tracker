package tracker;

public enum Courses {
    JAVA("Java", 600),
    DSA("DSA", 400),
    DATABASES("Databases", 480),
    SPRING("Spring", 550);

    private final String name;
    private final int maxPoints;

    Courses(String name, int maxPoints) {
        this.name = name;
        this.maxPoints = maxPoints;
    }

    public String getName() {
        return name;
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
