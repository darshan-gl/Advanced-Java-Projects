package Bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Bank {
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@//127.0.0.1:1521/orcl";
        String un = "scott";
        String pwd = "tiger";
        Connection con = null;

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            con = DriverManager.getConnection(url, un, pwd);
            Scanner sc = new Scanner(System.in);

            // Login Module
            System.out.println("<-----Welcome to Bank----->");
            System.out.println("Enter Account Number");
            int acc_num = sc.nextInt();
            System.out.println("Enter Pin");
            int pin = sc.nextInt();

            PreparedStatement pstmt1 = con.prepareStatement("SELECT * FROM account WHERE acc_num=? AND pin=?");
            pstmt1.setInt(1, acc_num);
            pstmt1.setInt(2, pin);
            ResultSet res1 = pstmt1.executeQuery();

            if (res1.next()) {
                String name = res1.getString(2);
                int bal = res1.getInt(4);
                System.out.println("Welcome " + name);
                System.out.println("Available balance is: " + bal);

                // Transfer details
                System.out.println("----------Transfer details--------");
                System.out.println("Enter beneficiary account number");
                int bacc_num = sc.nextInt();
                System.out.println("Enter transfer amount");
                int t_amount = sc.nextInt();

                PreparedStatement pstmt2 = con.prepareStatement("UPDATE account SET balance = balance - ? WHERE acc_num = ?");
                pstmt2.setInt(1, t_amount);
                pstmt2.setInt(2, acc_num);
                pstmt2.executeUpdate();

                System.out.println("----------Incoming Credit Request-------");
                System.out.println(name + " account no " + acc_num + " wants to transfer " + t_amount);
                System.out.println("Press Y to receive");
                System.out.println("Press N to reject");
                String choice = sc.next();

                if (choice.equalsIgnoreCase("Y")) {
                    PreparedStatement pstmt3 = con.prepareStatement("UPDATE account SET balance = balance + ? WHERE acc_num = ?");
                    pstmt3.setInt(1, t_amount);
                    pstmt3.setInt(2, bacc_num);
                    pstmt3.executeUpdate();

                    PreparedStatement pstmt4 = con.prepareStatement("SELECT * FROM account WHERE acc_num = ?");
                    pstmt4.setInt(1, bacc_num);
                    ResultSet res2 = pstmt4.executeQuery();
                    if (res2.next()) {
                        System.out.println("Updated balance is " + res2.getInt(4));
                    }
                } else {
                    PreparedStatement pstmt5 = con.prepareStatement("SELECT * FROM account WHERE acc_num = ?");
                    pstmt5.setInt(1, bacc_num);
                    ResultSet res2 = pstmt5.executeQuery();
                    if (res2.next()) {
                        System.out.println("Existing balance is " + res2.getInt(4));
                    }
                }
            } else {
                System.out.println("Invalid account number or pin.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

