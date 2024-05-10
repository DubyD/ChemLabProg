package com.example.backend;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The Admin class represents an administrator in the system who has the authority to manage students.
 * Admin inherits properties and methods from the User class.
 * @author yunjin
 */
public class Admin extends User {
    private List<User> students; // List to store the students managed by the admin.

    /**
     * Constructor for creating an Admin object.
     * Initializes the list of students.
     */
    public Admin() {
        this.students = new ArrayList<>();
    }

    /**
     * Adds a new student to the list of managed students.
     * @param username The username of the new student.
     * @param password The password of the new student.
     * @return True if the student is successfully added, false if the student already exists.
     */
    public boolean addStudent(String username, String password) {
        for (User student : this.students) {
            if (student.getUsername().equals(username)) {
                return false; // Student already exists
            }
        }
        User newStudent = new User(username, password);
        this.students.add(newStudent);
        return true; // Student added successfully
    }

    /**
     * Removes a student from the list of managed students.
     * @param username The username of the student to be removed.
     * @return True if the student is successfully removed, false if the student is not found.
     */
    public boolean removeStudent(String username) {
        for (User student : this.students) {
            if (student.getUsername().equals(username)) {
                students.remove(student);
                return true; // Student removed successfully
            }
        }
        return false; // Student not found
    }

    /**
     * Retrieves the list of currently managed students.
     * @return List of students managed by the admin.
     */
    public List<User> getCurrentStudents() {
        return students;
    }

    /**
     * Searches for takeout slips within a specified period.
     * @return List of takeout slips found within the specified period.
     */
    public List<TakeOutSlip> searchTakeoutSlips() {
        return new ArrayList<>(); // Placeholder return value
    }

    /**
     * Generates history for a specified period.
     * @param startDate The start date of the period.
     * @param endDate The end date of the period.
     * @return History for the specified period.
     */
    public String history(Date startDate, Date endDate) {
        return "History for the specified period"; // Placeholder return value
    }

    /**
     * Grants admin role to a user.
     * @param user The user to whom admin role will be granted.
     */
    public void grandAdminRole(User user) {
        user.setAdmin(true);
    }

    /**
     * Revokes admin role from a user.
     * @param user The user from whom admin role will be revoked.
     */
    public void revokeAdminRole(User user) {
        user.setAdmin(false);
    }

    /**
     * Gets the list of students managed by the admin.
     * @return List of students.
     */
    public List<User> getStudents() {
        return this.students;
    }

    /**
     * Sets the list of students managed by the admin.
     * @param students List of students to be managed.
     */
    public void setStudents(List<User> students) {
        this.students = students;
    }

    /**
     * Overrides the toString method to provide a string representation of the Admin object.
     * @return String representation of the Admin object.
     */
    @Override
    public String toString() {
        return "Admin{" +
                "students=" + students +
                '}';
    }
}
