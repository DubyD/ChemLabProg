import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *@author Yunjin
 */
public class Admin {
    private List<User> students;

    public Admin() {
        this.students = new ArrayList<>();
    }

    public boolean addStudent(String username, String password) {
        for (User student : students) {
            if (student.getUsername().equals(username)) {
                return false; // Student already exists
            }
        }
        User newStudent = new User(username, password);
        students.add(newStudent);
        return true; // Student added successfully
    }

    public boolean removeStudent(String username) {
        for (User student : students) {
            if (student.getUsername().equals(username)) {
                students.remove(student);
                return true; // Student removed successfully
            }
        }
        return false; // Student not found
    }

    public List<User> getCurrentStudents() {
        return students;
    }

    public List<TakeOutSlip> searchTakeoutSlips() {
        return new ArrayList<>(); // Placeholder return value
    }

    public String history(Date startDate, Date endDate) {
        return "History for the specified period"; // Placeholder return value
    }

    public void grandAdminRole(User user) {
        user.setAdmin(true);
    }

    public void revokeAdminRole(User user) {
        user.setAdmin(false);
    }

    public List<User> getStudents() {
        return students;
    }

    public void setStudents(List<User> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "students=" + students +
                '}';
    }
}
