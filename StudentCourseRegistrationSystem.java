import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    int id;
    ArrayList<String> registeredCourses = new ArrayList<>();

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    void registerCourse(String course) {
        registeredCourses.add(course);
    }

    void displayInfo() {
        System.out.println("Student ID: " + id);
        System.out.println("Student Name: " + name);
        System.out.println("Registered Courses: " + registeredCourses);
    }
}

class Course {
    String courseName;
    String courseCode;

    Course(String courseCode, String courseName) {
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    void displayCourse() {
        System.out.println("Course Code: " + courseCode);
        System.out.println("Course Name: " + courseName);
    }
}

public class StudentCourseRegistrationSystem {
    static ArrayList<Student> students = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeCourses();
        welcomeScreen();
        mainMenu();
    }

    static void initializeCourses() {
        courses.add(new Course("BTECH", "B. Tech"));
        courses.add(new Course("MTECH", "M. Tech"));
        courses.add(new Course("BCA", "BCA"));
        courses.add(new Course("MCA", "MCA"));
        courses.add(new Course("BBA", "BBA"));
        courses.add(new Course("MBA", "MBA"));
        courses.add(new Course("BCOM", "B. Com"));
        courses.add(new Course("BSC", "B. Sc"));
    }

    static void welcomeScreen() {
        System.out.println("******************************************************************");
        System.out.println("*                                                                *");
        System.out.println("*         WELCOME TO THE STUDENT COURSE REGISTRATION SYSTEM      *");
        System.out.println("*                                                                *");
        System.out.println("*                      _______________________                   *");
        System.out.println("*                     |                       |                  *");
        System.out.println("*                     |   COURSE REGISTRATION |                  *");
        System.out.println("*                     |_______________________|                  *");
        System.out.println("*                                                                *");
        System.out.println("*                    REGISTER AND MANAGE COURSES                 *");
        System.out.println("*                                                                *");
        System.out.println("*      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~      *");
        System.out.println("*                                                                *");
        System.out.println("*                PRESS ENTER TO START THE SYSTEM                 *");
        System.out.println("*                                                                *");
        System.out.println("******************************************************************");
        scanner.nextLine(); // Wait for user to press Enter
    }

    static void mainMenu() {
        while (true) {
            System.out.println("\n---- MAIN MENU ----");
            System.out.println("1. Register Student to Course");
            System.out.println("2. View Students");
            System.out.println("3. View Courses");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerStudentToCourse();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    viewCourses();
                    break;
                case 4:
                    System.out.println("Thank you for using the Course Registration System!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    static void registerStudentToCourse() {
        System.out.println("\n---- REGISTER STUDENT TO COURSE ----");

        // Prompt for new student details
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        // Create new student and add to the students list
        Student student = new Student(id, name);
        students.add(student);
        System.out.println("Student added successfully!");

        // Display available courses
        System.out.println("\nAvailable Courses:");
        for (Course course : courses) {
            System.out.println("Code: " + course.courseCode + " | Name: " + course.courseName);
        }

        // Prompt for course registration
        System.out.print("Enter Course Code: ");
        String courseCode = scanner.nextLine();
        Course course = findCourseByCode(courseCode);
        if (course == null) {
            System.out.println("Course not found! Please enter a valid course code.");
            return;
        }

        // Register student to the selected course
        student.registerCourse(course.courseName);
        System.out.println("Student registered to course successfully!");
    }

    static void viewStudents() {
        System.out.println("\n---- STUDENT LIST ----");
        for (Student student : students) {
            student.displayInfo();
            System.out.println("----------------------");
        }
    }

    static void viewCourses() {
        System.out.println("\n---- COURSE LIST ----");
        for (Course course : courses) {
            course.displayCourse();
            System.out.println("----------------------");
        }
    }

    static Course findCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
