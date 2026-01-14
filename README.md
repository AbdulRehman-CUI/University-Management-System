# University Management System (UMS)

A comprehensive Java-based University Management System implementing Object-Oriented Programming (OOP) principles to manage students, faculty, teaching assistants, courses, and departments.

## Features

- **User Management**: Register and manage students, faculty, and teaching assistants with role-based access.
- **Course Management**: Add courses, assign faculty, and handle student enrollments (fee-dependent).
- **Authentication**: Secure login system with password management.
- **Reporting**: Generate and export department reports (students, faculty, courses) to console or text files.
- **Data Persistence**: Automatic saving/loading of data using Java serialization.
- **Dual Interfaces**: Console-based menu system and Swing GUI for user-friendly interaction.
- **Demo Data**: Pre-loaded sample data for testing (5 students, 1 TA, 5 faculty, 5 courses).

## Technologies Used

- **Language**: Java
- **GUI Framework**: Swing (for UniversityGUI)
- **Data Storage**: Java Serialization (.ser files)
- **OOP Concepts**: Inheritance, Polymorphism, Abstraction, Encapsulation

## Installation & Setup

1. **Prerequisites**:
   - Java Development Kit (JDK) 8 or higher installed.
   - Ensure `javac` and `java` are in your PATH.

2. **Clone/Download**:
   - Download or clone the repository to your local machine.

3. **Compile**:
   - Navigate to the `src/` directory.
   - Run: `javac *.java`

4. **Run**:
   - **Console Version**: `java Main`
   - **GUI Version**: `java UniversityGUI`

## Usage

### Console Interface (Main.java)
- Follow the menu prompts to:
  - Register new students/courses.
  - View department reports.
  - Login as a student/TA to access dashboards or perform actions.
  - Export reports to text files.

### GUI Interface (UniversityGUI.java)
- Launch the graphical window with buttons for:
  - Registering students.
  - Adding courses.
  - Viewing reports.
  - Saving/exiting.

### Sample Workflow
1. Run the program (it loads/seeds demo data if none exists).
2. Register a student or add a course.
3. Login with a user ID (e.g., S101) and password.
4. View dashboards, change passwords, or (for TAs) assign grades.
5. Generate reports and export data.

## Project Structure

```
src/
├── Main.java                 # Console entry point and menu system
├── UniversityGUI.java        # Swing GUI interface
├── Person.java               # Abstract base class for users
├── Student.java              # Student class with enrollment and fees
├── Faculty.java              # Faculty class with grading/attendance
├── TeachingAssistant.java    # TA class (inherits from Student + Faculty ops)
├── Course.java               # Course management
├── Department.java           # Department aggregation and reporting
├── FacultyOperations.java    # Interface for faculty duties
├── DataManager.java          # Data serialization and export
├── SoundManager.java         # Audio feedback (disabled by default)
└── university_data.ser       # Serialized data file (auto-generated)
```

## Demo Data

On first run, the system seeds:
- **Students**: 5 regular students (IDs: S101-S105)
- **TA**: 1 Teaching Assistant (ID: TA500)
- **Faculty**: 5 professors
- **Courses**: 5 courses (CS101-CS105)

Login credentials (sample):
- Student: ID `S101`, Password `pass101`
- TA: ID `TA500`, Password `tapass`
