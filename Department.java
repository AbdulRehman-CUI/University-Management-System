import java.io.Serializable;
import java.util.ArrayList;
import java.io.Serializable;

class Department implements Serializable {
    // Attributes
    private String deptName;
    private String deptCode;

    // Lists(Aggregation, Weak Relation)
    private ArrayList<Course> offeredCourses;
    private ArrayList<Faculty> facultyList;
    private ArrayList<Student> studentList;

    //Constructors
    public Department(String deptName, String deptCode) {
        this.deptName = deptName;
        this.deptCode = deptCode;

        // Initialize empty lists
        this.offeredCourses = new ArrayList<>();
        this.facultyList = new ArrayList<>();
        this.studentList = new ArrayList<>();
    }

    // Getters
    public String getDeptName() {return deptName;}
    public String getDeptCode() {return deptCode;}
    public ArrayList<Course> getOfferedCourses() {return offeredCourses;}
    public void setOfferedCourses(ArrayList<Course> offeredCourses) {this.offeredCourses = offeredCourses;}
    public ArrayList<Student> getStudentList() {return studentList;}
    public void setStudentList(ArrayList<Student> studentList) {this.studentList = studentList;}
    public ArrayList<Faculty> getFacultyList() {return facultyList;}


    // 1. Add a Professor to the Department
    public void addFaculty(Faculty f) {
        facultyList.add(f);
        System.out.println("Faculty " + f.getName() + " joined " + this.deptName);
    }

    // 2. Add a Student to the Department
    public void addStudent(Student s) {
        studentList.add(s);
    }

    // 3. Add a new Course to the Department
    public void addCourse(Course c) {
        offeredCourses.add(c);
        System.out.println("New Course Added: " + c.getCourseName());
    }

    // 4. Display Method
    public void departmentReport() {
        System.out.println("\n==================================");
        System.out.println("       DEPARTMENT REPORT          : ");
        System.out.println("====================================");

        System.out.println("Total Faculty:  " + facultyList.size());
        System.out.println("Total Students: " + studentList.size());
        System.out.println("Courses Offered: " + offeredCourses.size());

        System.out.println("\n--- Course List ---");
        for (Course c : offeredCourses) {
            System.out.println("- " + c.getCourseName() + " (" + c.getCourseCode() + ")");
        }

        // Playing Sound
        // SoundManager.playSound("src/levelcomplete.wav");

        System.out.println("**************************************\n");
    }

    // Method for GUI to print the report on the screen rather than console
    public String getReportString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DEPARTMENT REPORT: ").append(deptName).append("\n");
        sb.append("==================================\n");
        sb.append("Total Students: ").append(studentList.size()).append("\n");
        sb.append("Total Faculty:  ").append(facultyList.size()).append("\n");
        sb.append("Courses Offered: ").append(offeredCourses.size()).append("\n\n");

        sb.append("--- COURSE LIST ---\n");
        for (Course c : offeredCourses) {
            sb.append(c.getCourseName()).append(" (Code: ").append(c.getCourseCode()).append(")\n");
        }

        sb.append("\n--- FACULTY LIST ---\n");
        for (Faculty f : facultyList) {
            sb.append(f.getName()).append(" (Dept: ").append(f.getDepartment()).append(")\n");
        }

        sb.append("\n--- STUDENT LIST ---\n");
        for (Student s : studentList) {
            sb.append(s.getName()).append(" (ID: ").append(s.getId()).append(")\n");
        }

        return sb.toString();
    }
}
