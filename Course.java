import java.io.Serializable;
import java.util.ArrayList;
import java.io.Serializable;

class Course implements Serializable {
    // Data Members
    private String courseCode;
    private String courseName;
    private Faculty assignedTeacher;
    private ArrayList<Student> enrolledStudents; // Aggregation(Weak Relation)
    private int maxCapacity;

    // Constructor
    public Course(String courseCode, String courseName, int maxCapacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.maxCapacity = maxCapacity;
        this.enrolledStudents = new ArrayList<>();
    }

    // Necessary Getters
    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getMaxCapacity() {return maxCapacity;}

    // 1. Assign a Teacher
    public void assignTeacher(Faculty teacher) {
        this.assignedTeacher = teacher;
        teacher.incrementCourseLoad();
        System.out.println("Teacher " + teacher.getName() + " assigned to " + this.courseName);
    }

    // 2. Add a Student
    public void addStudent(Student s) {
        if (enrolledStudents.size() < maxCapacity) {
            enrolledStudents.add(s);
            s.registerCourse(this.courseCode);
            System.out.println("Student " + s.getName() + " added to " + this.courseName);
        }
        else {
            System.out.println("Error: Course " + courseName + " is full.");
        }
    }

    // 3. Display Course Info...
    public void displayCourseDetails() {
        System.out.println("--- Course Details: " + courseName + " ---");
        System.out.println("Code: " + courseCode);
        if (assignedTeacher != null) {
            System.out.println("Teacher: " + assignedTeacher.getName());
        }
        else {
            System.out.println("Teacher Not Assigned to this Course Yet.");
        }
        System.out.println("Enrolled: " + enrolledStudents.size() + "/" + maxCapacity);
    }
}
