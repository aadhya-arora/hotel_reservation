import java.sql.*;
import java.util.Scanner;
public class Main {
    public static final String url="jdbc:mysql://localhost:3306/hotel_db";
    public static final String username="root";
    public static final String password="aadhya";

    public static void reserve(Connection connection,Scanner sc)
    {
        try{
            System.out.print("Enter name:");;
            String guest_name=sc.next();
            System.out.print("Room Number:");
            int room_number=sc.nextInt();
            System.out.print("Contact Number:");
            String contact_number=sc.next();
            String query="Insert into reservations(guest_name,room_number,contact_number) values (?,?,?)";
            PreparedStatement pstat=connection.prepareStatement(query);
            pstat.setString(1,guest_name );
            pstat.setInt(2, room_number);
            pstat.setString(3, contact_number);
            int rowsAffected=pstat.executeUpdate();
            if(rowsAffected>0)
            {
                System.out.println("Reservation confirmed");
            }
            else{
                System.out.println("There was an issue");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }
    public static void view(Connection connection,Scanner sc)
    {

    }
    public static void get(Connection connection,Scanner sc)
    {

    }
    public static void update(Connection connection,Scanner sc)
    {

    }
    public static void delete(Connection connection,Scanner sc)
    {

    }
    public static void exit()
    {

    }
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
            return;
        }
        try
        {
            Connection connection=DriverManager.getConnection(url, username, password);
            while(true)
            {
                System.out.println("  Hotel Management System  ");
                System.out.println("---------------------------");
                System.out.println("Choose an option");
                System.out.println("1.Reserve a room");
                System.out.println("2.View reservations");
                System.out.println("3.Get room number");
                System.out.println("4.Update reservations");
                System.out.println("5.Delete reservations");
                System.out.println("0.Exit");
                System.out.println("Your choice -");
                Scanner sc=new Scanner(System.in);
                int choice=sc.nextInt();
                switch(choice)
                {
                    case 1:
                    reserve(connection,sc);
                    break;
                    case 2:
                    view(connection,sc);
                    break;
                    case 3:
                    get(connection,sc);
                    break;
                    case 4:
                    update(connection , sc);
                    break;
                    case 5:
                    delete(connection,sc);
                    break;
                    case 0:
                    exit();
                    sc.close();
                    return;
                    default:
                    System.out.println("Wrong option");
                }

            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
}
