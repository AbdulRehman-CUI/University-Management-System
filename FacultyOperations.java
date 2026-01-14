public interface FacultyOperations {
    // Constants
    int PASSING_MARKS = 50;
    int MAX_MARKS = 100;

    // Methods
    void assignGrade(String studentId, String courseCode, double marks);

    void takeAttendance(String courseCode, String date);

    void viewCourseReport(String courseCode);
}
