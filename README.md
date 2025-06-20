# Academic Control System

This is a Spring Boot application for managing academic activities at Master Educational University. The system allows for the management of courses, disciplines, professors, students, class offerings, and enrollments.

## Features

- Course management (create, update, delete, view)
- Discipline management with prerequisites
- Professor management with qualifications
- Student management with enrollment status
- Class offerings for disciplines with scheduling
- Student enrollment in classes
- Grade and attendance tracking
- Automatic approval calculation based on grades and attendance
- Academic records and class diaries

## Technologies Used

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database (in-memory)
- Maven
- Lombok

## Project Structure

The project follows a standard Spring Boot architecture:

- **Model**: Entity classes representing the domain model
- **Repository**: JPA repositories for data access
- **Service**: Business logic layer
- **Controller**: REST API endpoints
- **Exception**: Global exception handling
- **Config**: Application configuration and data loading

## Class Diagram

The class diagram is available in PlantUML format in the `class-diagram.puml` file.

## API Endpoints

### Courses
- `GET /api/courses` - Get all courses
- `GET /api/courses/{id}` - Get course by ID
- `GET /api/courses/code/{code}` - Get course by code
- `POST /api/courses` - Create a new course
- `PUT /api/courses/{id}` - Update a course
- `DELETE /api/courses/{id}` - Delete a course
- `PUT /api/courses/{courseId}/coordinator/{professorId}` - Assign a coordinator to a course

### Professors
- `GET /api/professors` - Get all professors
- `GET /api/professors/{id}` - Get professor by ID
- `GET /api/professors/course/{courseId}` - Get professors by course
- `POST /api/professors` - Create a new professor
- `PUT /api/professors/{id}` - Update a professor
- `DELETE /api/professors/{id}` - Delete a professor
- `PUT /api/professors/{professorId}/courses/{courseId}` - Assign professor to course
- `DELETE /api/professors/{professorId}/courses/{courseId}` - Remove professor from course

### Disciplines
- `GET /api/disciplines` - Get all disciplines
- `GET /api/disciplines/{id}` - Get discipline by ID
- `GET /api/disciplines/code/{code}` - Get discipline by code
- `GET /api/disciplines/course/{courseId}` - Get disciplines by course
- `POST /api/disciplines/course/{courseId}` - Create a new discipline
- `PUT /api/disciplines/{id}` - Update a discipline
- `DELETE /api/disciplines/{id}` - Delete a discipline
- `PUT /api/disciplines/{disciplineId}/prerequisites/{prerequisiteId}` - Add prerequisite
- `DELETE /api/disciplines/{disciplineId}/prerequisites/{prerequisiteId}` - Remove prerequisite

### Students
- `GET /api/students` - Get all students
- `GET /api/students/{id}` - Get student by ID
- `GET /api/students/registration/{registrationNumber}` - Get student by registration number
- `GET /api/students/course/{courseId}` - Get students by course
- `GET /api/students/status/{status}` - Get students by status
- `POST /api/students/course/{courseId}` - Create a new student
- `PUT /api/students/{id}` - Update a student
- `DELETE /api/students/{id}` - Delete a student
- `PUT /api/students/{id}/status/{status}` - Update student status
- `PUT /api/students/{studentId}/course/{courseId}` - Change student course

### Class Offerings
- `GET /api/classes` - Get all class offerings
- `GET /api/classes/{id}` - Get class offering by ID
- `GET /api/classes/discipline/{disciplineId}` - Get class offerings by discipline
- `GET /api/classes/professor/{professorId}` - Get class offerings by professor
- `GET /api/classes/semester?year={year}&semester={semester}` - Get class offerings by year and semester
- `POST /api/classes/discipline/{disciplineId}` - Create a new class offering
- `PUT /api/classes/{id}` - Update a class offering
- `DELETE /api/classes/{id}` - Delete a class offering
- `PUT /api/classes/{classOfferingId}/professor/{professorId}` - Assign professor to class
- `DELETE /api/classes/{classOfferingId}/professor` - Remove professor from class

### Enrollments
- `GET /api/enrollments` - Get all enrollments
- `GET /api/enrollments/{id}` - Get enrollment by ID
- `GET /api/enrollments/student/{studentId}` - Get enrollments by student
- `GET /api/enrollments/class/{classOfferingId}` - Get enrollments by class offering
- `POST /api/enrollments/student/{studentId}/class/{classOfferingId}` - Enroll student in class
- `DELETE /api/enrollments/{id}` - Unenroll student
- `PUT /api/enrollments/{id}/grades` - Update grades and attendance
- `GET /api/enrollments/class/{classOfferingId}/diary` - Get class diary
- `GET /api/enrollments/student/{studentId}/academic-record` - Get student academic record

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven

### Running the Application
1. Clone the repository
2. Navigate to the project directory
3. Run `mvn spring-boot:run`
4. The application will start on http://localhost:8080
5. Access the H2 database console at http://localhost:8080/h2-console
   - JDBC URL: jdbc:h2:mem:academicdb
   - Username: sa
   - Password: password

## Sample Data

The application is configured with the `dev` profile, which loads sample data on startup. This includes:
- 2 courses (Computer Science and Information Systems)
- 2 professors
- 3 disciplines
- 2 students
- 2 class offerings
- 2 enrollments with grades

## License

This project is licensed under the MIT License.
