import java.io.*;
import java.util.*;

public class StudyHub implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<User> users;
    private List<Course> courses;

    public StudyHub() {
        this.users = new ArrayList<>();
        this.courses = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void deleteUser(int userId) {
        users.removeIf(user -> user.getId() == userId);
    }

    public void deleteCourse(String courseId) {
        courses.removeIf(course -> course.getCourseId().equals(courseId));
    }

    public User searchUser(int userId) {
        for (User user : users) {
            if (user.getId() == userId) return user;
        }
        return null;
    }

    public Course searchCourse(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) return course;
        }
        return null;
    }

    public void saveDataToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static StudyHub loadDataFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (StudyHub) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
