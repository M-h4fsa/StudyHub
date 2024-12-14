import java.io.Serializable;
import java.util.*;

public class Course implements Serializable {
    private static final long serialVersionUID = 1L;

    private String courseId;
    private String title;
    private String description;
    private List<Student> students;

    public Course(String courseId, String title, String description) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.students = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public void displayDetails() {
        System.out.println("Course ID: " + courseId);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Enrolled Students: " + students.size());
    }
}
