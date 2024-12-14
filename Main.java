import java.util.Scanner;

public class Main {
    private static final String TUTOR_PASSWORD = "3208";
    private static final String DEVELOPER_PASSWORD = "9979";
    private static final String DATA_FILE = "studyhub.dat";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudyHub hub;

        // Load data from file
        hub = StudyHub.loadDataFromFile(DATA_FILE);
        if (hub == null) {
            hub = new StudyHub();
            printLine();
            System.out.println("📁 No previous data found. Starting with an empty StudyHub.");
        } else {
            printLine();
            System.out.println("✅ Data loaded successfully!");
        }

        while (true) {
            printLine();
            System.out.println("🌟 Welcome to **StudyHub** 🌟");
            printLine();
            System.out.println("👤 Choose your role:");
            System.out.println("1️⃣  Student 🎓");
            System.out.println("2️⃣  Tutor 📚");
            System.out.println("3️⃣  Developer 💻");
            System.out.println("4️⃣  Exit 🚪");
            printLine();
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    studentMenu(scanner, hub);
                    break;
                case 2:
                    tutorMenu(scanner, hub);
                    break;
                case 3:
                    developerMenu(scanner, hub);
                    break;
                case 4:
                    System.out.println("💾 Saving data...");
                    hub.saveDataToFile(DATA_FILE);
                    System.out.println("✅ Data saved successfully. Goodbye! 👋");
                    printLine();
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private static void studentMenu(Scanner scanner, StudyHub hub) {
        printLine();
        System.out.println("🎓 **Student Menu** 🎓");
        printLine();
        System.out.println("🔔 Note: Please enter an **odd-numbered ID** to proceed.");
        System.out.print("Enter your User ID: ");
        int userId = scanner.nextInt();
        if (userId % 2 == 0) { // Validation for odd IDs
            System.out.println("❌ Invalid ID. Student IDs must be odd numbers.");
            return;
        }
        User user = hub.searchUser(userId);
        if (user == null || !(user instanceof Student)) {
            System.out.println("❌ Invalid Student ID.");
            return;
        }
        Student student = (Student) user;

        while (true) {
            printLine();
            System.out.println("📋 **Options**:");
            System.out.println("1️⃣  Enroll in a Course 📖");
            System.out.println("2️⃣  Search for a Course 🔍");
            System.out.println("3️⃣  Update Progress 📝");
            System.out.println("4️⃣  Display Enrolled Courses 🗂️");
            System.out.println("5️⃣  Back to Main Menu 🔙");
            printLine();
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("📘 Enter Course ID to Enroll: ");
                    String courseId = scanner.next();
                    Course course = hub.searchCourse(courseId);
                    if (course != null) {
                        student.enrollCourse(course);
                        System.out.println("✅ Enrolled in " + course.getTitle());
                    } else {
                        System.out.println("❌ Course not found.");
                    }
                    break;
                case 2:
                    System.out.print("🔍 Enter Course ID to Search: ");
                    String searchId = scanner.next();
                    Course searchedCourse = hub.searchCourse(searchId);
                    if (searchedCourse != null) {
                        searchedCourse.displayDetails();
                    } else {
                        System.out.println("❌ Course not found.");
                    }
                    break;
                case 3:
                    System.out.print("📝 Enter Course ID to Update Progress: ");
                    String progressCourseId = scanner.next();
                    Course progressCourse = hub.searchCourse(progressCourseId);
                    if (progressCourse != null) {
                        System.out.print("📊 Enter Progress Percentage: ");
                        int progress = scanner.nextInt();
                        student.updateProgress(progressCourse, progress);
                        System.out.println("✅ Progress updated.");
                    } else {
                        System.out.println("❌ Course not found.");
                    }
                    break;
                case 4:
                    student.displayDetails();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private static void tutorMenu(Scanner scanner, StudyHub hub) {
        printLine();
        System.out.print("🔑 Enter Tutor Password: ");
        String password = scanner.next();
        if (!password.equals(TUTOR_PASSWORD)) {
            System.out.println("❌ Incorrect password.");
            return;
        }

        printLine();
        System.out.println("🔔 Note: Please enter an **even-numbered ID** to proceed.");
        System.out.print("Enter your User ID: ");
        int userId = scanner.nextInt();
        if (userId % 2 != 0) { // Validation for even IDs
            System.out.println("❌ Invalid ID. Tutor IDs must be even numbers.");
            return;
        }
        User user = hub.searchUser(userId);
        if (user == null || !(user instanceof Instructor)) {
            System.out.println("❌ Invalid Tutor ID.");
            return;
        }
        Instructor tutor = (Instructor) user;

        while (true) {
            printLine();
            System.out.println("📚 **Tutor Menu** 📚");
            System.out.println("1️⃣  Assign a Course ✍️");
            System.out.println("2️⃣  List Assigned Courses 📋");
            System.out.println("3️⃣  Back to Main Menu 🔙");
            printLine();
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("📘 Enter Course ID to Assign: ");
                    String courseId = scanner.next();
                    Course course = hub.searchCourse(courseId);
                    if (course != null) {
                        tutor.assignCourse(course);
                        System.out.println("✅ Course assigned successfully.");
                    } else {
                        System.out.println("❌ Course not found.");
                    }
                    break;
                case 2:
                    tutor.listAssignedCourses();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private static void developerMenu(Scanner scanner, StudyHub hub) {
        printLine();
        System.out.print("🔑 Enter Developer Password: ");
        String password = scanner.next();
        if (!password.equals(DEVELOPER_PASSWORD)) {
            System.out.println("❌ Incorrect password.");
            return;
        }

        while (true) {
            printLine();
            System.out.println("💻 **Developer Menu** 💻");
            System.out.println("1️⃣  Add a User 👤");
            System.out.println("2️⃣  Remove a User 🗑️");
            System.out.println("3️⃣  Add a Course 📚");
            System.out.println("4️⃣  Remove a Course 🗑️");
            System.out.println("5️⃣  Display User Info ℹ️");
            System.out.println("6️⃣  Back to Main Menu 🔙");
            printLine();
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("👤 Enter User Type (1-Student, 2-Tutor): ");
                    int type = scanner.nextInt();
                    System.out.print("🆔 Enter User ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("📛 Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("📧 Enter Email: ");
                    String email = scanner.nextLine();

                    if (type == 1 && id % 2 == 0) {
                        System.out.println("❌ Invalid ID. Student IDs must be odd.");
                    } else if (type == 2 && id % 2 != 0) {
                        System.out.println("❌ Invalid ID. Tutor IDs must be even.");
                    } else if (type == 1) {
                        hub.addUser(new Student(id, name, email));
                        System.out.println("✅ Student added successfully.");
                    } else if (type == 2) {
                        hub.addUser(new Instructor(id, name, email));
                        System.out.println("✅ Tutor added successfully.");
                    } else {
                        System.out.println("❌ Invalid User Type.");
                    }
                    break;

                case 2:
                    System.out.print("🆔 Enter User ID to Remove: ");
                    int userId = scanner.nextInt();
                    hub.deleteUser(userId);
                    System.out.println("🗑️ User removed if they existed.");
                    break;

                case 3:
                    System.out.print("📘 Enter Course ID: ");
                    String courseId = scanner.next();
                    scanner.nextLine();
                    System.out.print("📖 Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("📝 Enter Description: ");
                    String description = scanner.nextLine();
                    hub.addCourse(new Course(courseId, title, description));
                    System.out.println("✅ Course added successfully.");
                    break;

                case 4:
                    System.out.print("🆔 Enter Course ID to Remove: ");
                    String removeId = scanner.next();
                    hub.deleteCourse(removeId);
                    System.out.println("🗑️ Course removed if it existed.");
                    break;

                case 5:
                    System.out.print("🆔 Enter User ID: ");
                    int searchId = scanner.nextInt();
                    User user = hub.searchUser(searchId);
                    if (user != null) {
                        printLine();
                        user.displayDetails();
                    } else {
                        System.out.println("❌ User not found.");
                    }
                    break;

                case 6:
                    System.out.println("🔙 Returning to Main Menu...");
                    return;

                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    private static void printLine() {
        System.out.println("===============================================");
    }
}
