import java.sql.*;
import java.util.Scanner;
public class Main {
    public static final String url="jdbc:mysql://localhost:3306/hotel_db";
    public static final String username="root";
    public static final String password="aadhya";

    public static void updateName(Connection connection,Scanner sc)
    {
        try {
            System.out.println("Enter the guest id:");
            int id=sc.nextInt();
            System.out.println("Updated name:");
            String name=sc.next();
            String query="Update reservations set guest_name=? where reservation_id=?";
            PreparedStatement pstat=connection.prepareStatement(query);
            pstat.setString(1, name);
            pstat.setInt(2, id);
            int rowsAffected=pstat.executeUpdate();
            if(rowsAffected>0)
            {
                System.out.println("Guest Name successfully updated");
            }
            else
            {
                System.out.println("Guest name not updated");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void updateRoom(Connection connection,Scanner sc)
    {
        try
        {
            System.out.println("Enter the guest id:");
            int id=sc.nextInt();
            System.out.println("Update room to:");
            int room_number=sc.nextInt();
            String query="Update reservations set room_number=? where reservation_id=?";
            PreparedStatement pstat=connection.prepareStatement(query);
            pstat.setInt(1, room_number);
            pstat.setInt(2, id);
            int rowsAffected=pstat.executeUpdate();
            if(rowsAffected>0)
            {
                System.out.println("Room number updated successfully");
            }
            else
            {
                System.out.println("Room number not updated successfully");
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void updateContact(Connection connection,Scanner sc)
    {
        try {
            System.out.println("Enter the guest id:");
            int id=sc.nextInt();
            System.out.println("Updated Contact No:");
            String contact=sc.next();
            String query="Update reservations set contact_number=? where reservation_id=?";
            PreparedStatement pstat=connection.prepareStatement(query);
            pstat.setInt(2,id);
            pstat.setString(1, contact);
            int rowsAffected=pstat.executeUpdate();
            if(rowsAffected>0)
            {
                System.out.println("Contact Info successfully updated");
            }
            else
            {
                System.out.println("There is an issue updating the contact info");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

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
        try {
            System.out.print("Enter the unique id:");
            int id=sc.nextInt();
            String query="Select * from reservations where reservation_id=?";
            PreparedStatement pstat=connection.prepareStatement(query);
            pstat.setInt(1, id);
            ResultSet rSet=pstat.executeQuery();
            if(rSet.next())
            {
                int reservation_id=rSet.getInt("reservation_id");
                String guest_name=rSet.getString("guest_name");
                int room_number=rSet.getInt("room_number");
                String contact_number=rSet.getString("contact_number");
                System.out.println("Reservation Id:"+reservation_id);
                System.out.println("Guest name:"+guest_name);
                System.out.println("Room Number:"+room_number);
                System.out.println("Contact Number:"+contact_number);
            }
            else
            {
                System.out.println("No such booking");
            }
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }

    }
    public static void get(Connection connection,Scanner sc)
    {
        try {
            System.out.println("Enter the Reservation ID");
            int id=sc.nextInt();
            String query="Select * from reservations where reservation_id=?";
            PreparedStatement pstat=connection.prepareStatement(query);
            pstat.setInt(1, id);
            ResultSet rset=pstat.executeQuery();
            if(rset.next())
            {
                int room_number=rset.getInt("room_number");
                System.out.println("Room Number:"+room_number);
            }
            else
            {
                System.out.println("Not booked yet");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void update(Connection connection,Scanner sc)
    {
        System.out.println("----------------------------------");
        System.out.println(" What Info do you want to update? ");
        System.out.println("----------------------------------");
        System.out.println("1.Guest name");
        System.out.println("2.Room number");
        System.out.println("3.Contact info");
        int choice=sc.nextInt();
        switch (choice) {
            case 1:
                updateName(connection,sc);
                break;
            case 2:
                updateRoom(connection,sc);
                break;
            case 3:
                updateContact(connection,sc);
                break;
            default:
                break;
        }
    }
    public static void delete(Connection connection,Scanner sc)
    {
        try {
            System.out.println("Which booking do you want to delete:");
            int id=sc.nextInt();
            String query="Delete from reservations where reservation_id=?";
            PreparedStatement pstat=connection.prepareStatement(query);
            pstat.setInt(1, id);
            int rset=pstat.executeUpdate();
            if(rset>0)
            {
                System.out.println("Booking successfully deleted");
            }
            else
            {
                System.out.println("No such reservation");
            }
            
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
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
                System.out.println("---------------------------");
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
