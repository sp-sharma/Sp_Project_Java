package org.example;
import org.example.modules.studentinfo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int choose = 1; // Assigning a value to choose for testing
        Scanner scanner = new Scanner(System.in);
        //data of student
        studentinfo stdinfo = new studentinfo();
        //variable for connecting database
        String Url = "jdbc:sqlite:student.db";
        //variable for creating database
        String StudentTable = "CREATE TABLE IF NOT EXISTS Student " + "(" +
                "id integer PRIMARY KEY AUTOINCREMENT, " + // Change to AUTOINCREMENT for automatic id generation
                "name text," +
                "rollno integer," +
                "contact integer," +
                "grade integer)";
        //variable for inserting data in database
        String StudentInfo =  "INSERT INTO Student(name,rollno,contact,grade) Values(?,?,?,?)";
        //variable for selecting all data from database
        String SelectAllData = "SELECT * FROM Student";
        //variable for updating contact data in database
        String UpdateContactData =  "UPDATE Student SET contact = ? WHERE id = ?";
        //variable for deleting data from database
        String DeleteData = "DELETE FROM Student WHERE id = ?";
        try {
            //connection to database
            Connection connection = DriverManager.getConnection(Url);
            //creating tables by calling query
            Statement statement = connection.createStatement();
            while (true){
                System.out.println("1. Check connection");
                System.out.println("2. Insert Data");
                System.out.println("3. Show all data");
                System.out.println("4. Update data in table");
                System.out.println("5. Delete data from table");
                System.out.print("Choose number from 1 to 10 : ");
                choose = scanner.nextInt();
                if (choose == 0){
                    System.out.println("Database is connected!!!");
                }
                else if(choose == 1){
                    statement.execute(StudentTable);
                    System.out.println("Student Table Created");
                }
                else if(choose == 2){
                    //inserting data from objects
                    PreparedStatement pst = connection.prepareStatement(StudentInfo);
                    System.out.print("Enter name : ");
                    stdinfo.setName(scanner.next());
                    System.out.print("Enter Roll no : ");
                    stdinfo.setRollno(scanner.nextInt());
                    System.out.print("Enter contact : ");
                    stdinfo.setContact(scanner.nextLong());
                    System.out.print("Enter grade : ");
                    stdinfo.setGrade(scanner.nextInt());
                    pst.setString(1,stdinfo.getName());
                    pst.setInt(2,stdinfo.getRollno());
                    pst.setLong(3,stdinfo.getContact());
                    pst.setInt(4, stdinfo.getGrade());
                    pst.executeUpdate(); // Use executeUpdate for INSERT queries
                    System.out.println("Data inserted successful");
                }
                else if(choose == 3){
                    // Selecting all data from the Student table
                    ResultSet rs = statement.executeQuery(SelectAllData);
                    while (rs.next()){
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        int rollNo = rs.getInt("rollno"); // Corrected column name to match the table definition
                        int contact = rs.getInt("contact");
                        int grade = rs.getInt("grade");
                        System.out.println("Id: " + id + ", Name: " + name + ", Roll No: " + rollNo + ", Contact: " + contact + ", Grade: " + grade);
                    }
                }
                else if(choose == 4){
                    // Updating contact data in the Student table
                    PreparedStatement pst = connection.prepareStatement(UpdateContactData);
                    pst.setInt(1, 987654); // change new contact
                    int number = scanner.nextInt();
                    pst.setInt(2, number); // write id which you want to change
                    pst.executeUpdate(); // running updating query
                    System.out.println("Contact data updated");
                }
                else if(choose == 5){
                    // Deleting data from the Student table
                    PreparedStatement pst = connection.prepareStatement(DeleteData);
                    int number = scanner.nextInt();
                    pst.setInt(1, number); // write id which you want to delete
                    pst.executeUpdate(); // running delete query
                    System.out.println("Data deleted successfully");
                }
                else{
                    System.out.println("Invalid choice number");
                    break;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}
