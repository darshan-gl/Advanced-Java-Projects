import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBOperations {
    static Connection con = null;
    static Employees e = new Employees();
    static void save(String tableName, Employees emp) {
        try {
            con = JdbcUtil.getConnection();
            String query = "INSERT INTO "+tableName+"(EMP_ID, EMP_NAME, salary,DEPT_ID, MANAGER_ID) "
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setInt(1, emp.getEMP_ID());
            pstmt.setString(2, emp.getEMP_NAME());
            pstmt.setInt(3, emp.getSalary());
            pstmt.setInt(4, emp.getDepartment_ID());
            pstmt.setInt(5, emp.getManager_id());
            int rpws = pstmt.executeUpdate();
            if(rpws > 0) {
                System.out.println("Employee added successfully.");
            } else {
                System.out.println("Failed to add employee.");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }



    static void update(String tableName, Employees emp, int empId) {
        try {
            Connection con = JdbcUtil.getConnection();
            String query = "UPDATE " + tableName +
                    " SET EMP_NAME = ?, salary =?, DEPT_ID = ? , Manager_Id = ?"
                    + "WHERE EMP_ID = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, emp.getEMP_NAME());
            pstmt.setInt(2, emp.getSalary());
            pstmt.setInt(3, emp.getDepartment_ID());
            pstmt.setInt(4, emp.getManager_id());
            pstmt.setInt(5, empId);
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Failed to update employee.");
            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void delete(String tableName, int empId) {
        try {
            Connection con = JdbcUtil.getConnection();
            String query = "DELETE FROM " + tableName + " WHERE EMP_ID = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, empId);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Failed to delete employee.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void getEmployee(String tableName) {
        try {
            Connection con = JdbcUtil.getConnection();
            String query = "SELECT * FROM " + tableName ;
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                System.out.println("------------------------------");
                System.out.println("Employee ID: " + rs.getInt("EMP_ID"));
                System.out.println("Employee Name: " + rs.getString("EMP_NAME"));
                System.out.println("Employee Salary: " + rs.getInt("salary"));
                System.out.println("Employee Department ID: " + rs.getInt("DEPT_ID"));
                System.out.println("Employee Manager ID: " + rs.getInt("MANAGER_ID"));
                System.out.println("-----------------------------");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
