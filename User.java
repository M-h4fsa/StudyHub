import java.io.Serializable;

public class User implements Serializable, Comparable<User> {
    private static final long serialVersionUID = 1L;

    protected int id;
    protected String name;
    protected String email;

    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
    }

    @Override
    public int compareTo(User otherUser) {
        return Integer.compare(this.id, otherUser.id);
    }

    public int getId() {
        return id;
    }
}
