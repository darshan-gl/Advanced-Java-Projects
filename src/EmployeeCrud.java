
import java.sql.Connection;
import java.util.Scanner;

public class EmployeeCrud {

    static Connection con = null;
    static String tableName = "EMPLOYEES";

    public static void addEmployee(Employees emp) {
        DBOperations.save(tableName, emp);
    }

    public static void updateEmployee(Employees emp, int empId) {
        DBOperations.update(tableName, emp, empId);
    }

    public static void deleteEmployee(int empid) {
        DBOperations.delete(tableName, empid);
    }

    public static void getEmployee() {
        DBOperations.getEmployee(tableName);
    }

    public static void main(String[] args) throws Exception {
        con = JdbcUtil.getConnection();
        Scanner scan = new Scanner(System.in);
        Employees emp = new Employees();
        do {
            System.out.println("1. Create Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Delete Employee");
            System.out.println("4. Get Employee");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");
            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter Employee Id:");
                    emp.setEMP_ID(scan.nextInt());

                    System.out.println("Enter Employee NAme:");
                    scan.nextLine(); // Consume the newline character left by nextInt()
                    emp.setEMP_NAME(scan.nextLine());

                    System.out.println("Enter Employee Salary:");
                    emp.setSalary(scan.nextInt());

                    System.out.println("Enter Employee Department Id:");
                    emp.setDepartment_ID(scan.nextInt());

                    System.out.println("Enter Employee Manager Id:");
                    emp.setManager_id(scan.nextInt());
                    addEmployee(emp);
                    break;
                case 2:
                    System.out.println("Enter the Employee Id: ");
                    int empId = scan.nextInt();

                    System.out.println("Enter Employee Name:");
                    scan.nextLine(); // Consume the newline character left by nextInt()
                    emp.setEMP_NAME(scan.nextLine());

                    System.out.println("Enter Employee Salary:");
                    emp.setSalary(scan.nextInt());

                    System.out.println("Enter Employee Department Id:");
                    emp.setDepartment_ID(scan.nextInt());

                    System.out.println("Enter Employee Manager Id:");
                    emp.setManager_id(scan.nextInt());

                    updateEmployee(emp, empId);
                    break;
                case 3:
                    System.out.println("Enter the Employee Id to delete: ");
                    int empid = scan.nextInt();
                    deleteEmployee(empid);
                    break;
                case 4:
                    getEmployee();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

        }while(true);

    }
}