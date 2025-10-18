# Platforma Studiu
 This project is a comprehensive student management platform built with JavaFX and backed by a MySQL database. It offers a desktop application with
  role-based access for Students, Professors, and Administrators to streamline academic activities.
<table width="100%">
  <tr>
    <td width="33%">
     <img src="https://github.com/iPanda64/platforma-studiu/blob/main/Screenshots/Picture1.png" />
    </td>
    <td width="33%">
      <img src="https://github.com/iPanda64/platforma-studiu/blob/main/Screenshots/Picture2.png" />
    </td>
    </td>
    <td width="33%">
     <img src="https://github.com/iPanda64/platforma-studiu/blob/main/Screenshots/Picture3.png" />
    </td>
  </tr>
  <tr>
    <td width="33%">
      <img src="https://github.com/iPanda64/platforma-studiu/blob/main/Screenshots/Picture4.png" />
    </td>
    <td width="33%">
     <img src="https://github.com/iPanda64/platforma-studiu/blob/main/Screenshots/Picture5.png" />
    </td>
    </td>
    <td width="33%">
     <img src="https://github.com/iPanda64/platforma-studiu/blob/main/Screenshots/Picture6.png" />
    </td>
  </tr>
</table>


<details>
<summary>View Diagrams</summary>

![UML Diagram](https://github.com/iPanda64/platforma-studiu/blob/main/UML.drawio.png)
![Use Case Diagram](https://github.com/iPanda64/platforma-studiu/blob/main/UseCaseDiagram.png)

</details>

## Features by User Role

User accounts are created by an Administrator. Once logged in, each user sees a tailored interface with the tools relevant to their role.

### 🎓 Student
- **Profile:** View personal information (read-only).  
- **Dashboard:** See a personalized calendar with activities and deadlines.  
- **Course Enrollment:** Browse courses and enroll in subjects and activities.  
- **Grades:** Track detailed grades and final scores.  
- **Group Collaboration:** Join or create study groups, communicate via messaging, and manage group-specific activities.

### 👨‍🏫 Professor
- **Schedule Management:** View and manage teaching activities in a personal calendar.  
- **Grading:** Input, update, and manage grades for students across various activities like exams, labs, and projects
- **Course Catalog:** View and download grade catalogs for assigned courses.  
- **Course Control:** Define course structure by adding activities and setting grading weights.  
- **Scheduling:** Program new activities and events in the academic calendar for students to see.
  
 ### ⚙️ Administrator
- **User Management:** Create, update, and delete Student and Professor accounts.  
- **Course & Activity Oversight:** Manage the full course catalog and create new activities.  
- **Professor Assignment:** Assign professors to courses and activities.  
- **Data Access:** View detailed information for all users, courses, and activities.

### 👑 Super-Administrator
- **Top-Level Control:** All Administrator capabilities.  
- **Admin Management:** Manage Administrator accounts to maintain a secure chain of command.

## Prerequisites

-   JDK 17 or later
-   Apache Maven
-   MySQL Server 8.0+ (ensure it is running and accessible)

## Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/iPanda64/platforma-studiu.git
   cd platforma-studiu
   ```

2. **Set up the MySQL database**
   - Start your MySQL server.
   - Log into MySQL:
     ```bash
     mysql -u root -p
     ```
   - Create the database used by the project:
     ```sql
     CREATE DATABASE platforma_studiu;
     ```
   - Import the provided SQL file (located in the project root) to create tables and insert initial data:
     ```bash
     mysql -u root -p platforma_studiu < platforma-studiu-dump.sql
     ```

3. **Database configuration**
   - The project is preconfigured to connect to MySQL using:
     ```
     URL: jdbc:mysql://127.0.0.1:3306/platforma_studiu
     Username: root
     Password: root
     ```
   - Ensure your MySQL server is running locally and that these credentials are valid.  
  - If you need to change them, edit the following file:
     ```
     platforma-studiu/src/PlatformaStudiu/Query.java
     ```
     Update the `url`, `uid`, and `pw` variables with your own database details.

4. **Build and run the project**
   ```bash
   mvn clean javafx:run
## Default Login Credentials

For testing or initial use, you can log in with the following accounts:

| Role       | Username | Password |
|------------|----------|----------|
| Student    | george.ionescu | password2 |
| Professor  | elena.ionescu | 1234 |
| Administrator | marina.stefan | admin1 |
| Super-Administrator | rares.vlad | superadmin1 |

> All accounts are stored in the MySQL database. You can create or modify users through the Administrator interface.
## Display Recommendation

- For the best visual experience, set your Windows display scaling to **125%**.  
- Use a screen with a **16:10 aspect ratio** if possible.  
- This ensures all JavaFX UI elements are displayed correctly.
- ## Contributors

This project was developed by two students:

- **[Souca Vlad](https://github.com/tPilgrim)** – Designed and implemented the **User Interface**.  
- **[Buleu Mihai](https://github.com/iPanda64)** – Developed the **database** and the **backend functionality** including user roles, data management and application logic, created **documentation**, and contributed slightly to the UI.

