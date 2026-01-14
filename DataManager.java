import java.io.*;

class DataManager {
    // File Name
    private static final String FILE_NAME = "university_data.ser";

    // Serialization
    public static void saveData(Department dept) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(dept);
            System.out.println("Data saved successfully to " + FILE_NAME);
        }
        catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Deserialization
    public static Department loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Department dept = (Department) ois.readObject();
            System.out.println("Data loaded successfully!");
            return dept;
        }
        catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
            return null;
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
            return null;
        }
    }

    // NEW: Method to export readable text
    public static void exportReportToText(Department dept) {
        String fileName = "University_Report.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            // 1. Header
            writer.println("==========================================");
            writer.println("   UNIVERSITY DEPARTMENT REPORT");
            writer.println("==========================================");
            writer.println("Department: " + dept.getDeptName());
            writer.println("Code:       " + dept.getDeptCode());
//            writer.println("Teacher : " + dept.ge;
//            writer.println("Teacher : " + Faculty.getName());
            writer.println("Generated:  " + new java.util.Date());
            writer.println("------------------------------------------\n");

            // 2. Student Section
            writer.println("--- REGISTERED STUDENTS (" + dept.getStudentList().size() + ") ---");
            if (dept.getStudentList().isEmpty()) {
                writer.println("(No students registered)");
            } else {
                for (Student s : dept.getStudentList()) {
                    writer.println(String.format("ID: %-10s | Name: %-20s | CGPA: %.2f",
                            s.getId(), s.getName(), s.getCgpa()));
                }
            }

            writer.println("\n------------------------------------------");

            // 3. Course Section
            writer.println("--- COURSE CATALOG (" + dept.getOfferedCourses().size() + ") ---");
            if (dept.getOfferedCourses().isEmpty()) {
                writer.println("(No courses added)");
            }
            else {
                for (Course c : dept.getOfferedCourses()) {
                    writer.println("Code: " + c.getCourseCode() + " | Name: " + c.getCourseName());
                }
            }

            writer.println("\n------------------------------------------");

            // 4. Faculty Section
            writer.println("--- FACULTY LIST (" + dept.getFacultyList().size() + ") ---");
            if (dept.getFacultyList().isEmpty()) {
                writer.println("(No faculty added)");
            }
            else {
                for (Faculty f : dept.getFacultyList()) {
                    writer.println("Name: " + f.getName() + " | Dept: " + f.getDepartment() + " | Salary: $" + f.getSalary());
                }
            }

            writer.println("==========================================");

            // Play Sound
            // SoundManager.playSound("src/levelcomplete.wav");

            System.out.println("Success! Readable report saved to: " + fileName);

        }
        catch (IOException e) {
            System.out.println("Error writing text file: " + e.getMessage());
        }
    }
}
