import java.io.Serializable;
import java.util.*;

public class Student extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Course> enrolledCourses;
    private Map<String, Integer> progress;

    public Student(int id, String name, String email) {
        super(id, name, email);
        this.enrolledCourses = new ArrayList<>();
        this.progress = new HashMap<>();
    }

    public void enrollCourse(Course course) {
        enrolledCourses.add(course);
        progress.put(course.getCourseId(), 0);
        course.addStudent(this);
    }

    public void updateProgress(Course course, int percentage) {
        if (progress.containsKey(course.getCourseId())) {
            progress.put(course.getCourseId(), percentage);
        }
    }

    public void displayDetails() {
        super.displayDetails();
        System.out.println("Enrolled Courses: ");
        for (Course c : enrolledCourses) {
            System.out.println(" - " + c.getTitle());
        }
    }
}
