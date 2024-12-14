import java.io.Serializable;
import java.util.*;

public class Instructor extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Course> assignedCourses;

    public Instructor(int id, String name, String email) {
        super(id, name, email);
        this.assignedCourses = new ArrayList<>();
    }

    public void assignCourse(Course course) {
        assignedCourses.add(course);
    }

    public void listAssignedCourses() {
        System.out.println("Assigned Courses:");
        for (Course c : assignedCourses) {
            System.out.println(" - " + c.getTitle());
        }
    }
}
