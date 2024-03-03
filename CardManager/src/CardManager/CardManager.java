/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CardManager;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author AluCiclesGS1
 */
public class CardManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here

        // Register the PostgreSQL driver
        menu();
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        System.out.println("You have entered option: " + option);

        switch (option) {

            case 1:
                Exercise1();
                break;

            case 2:
                Exercise2();
                break;

            case 3:
                Exercise3();
                break;

            case 4:
                Exercise4();
                break;
      
            default:
                break;
        }

    }
    // Menu to create cards, etc., so the user knows what to choose
    private static void menu() {
        System.out.println("******MENU******");
        System.out.println("1.- Exercise 1: List the cards");
        System.out.println("2.- Exercise 2: Add a card");
        System.out.println("3.- Exercise 3: Modify a card");
        System.out.println("4.- Exercise 4: Delete a card");
    }
    // Exercise 1
    private static void Exercise1() throws SQLException {
        System.out.println("1.- Exercise 1: List the cards ");
        try {
            Class.forName("org.postgresql.Driver");
            // In case the driver is not found
        } catch (ClassNotFoundException ex) {
            System.out.println("Error registering PostgreSQL driver: " + ex);
        }

        Connection connection = null;
        // Database connect
        connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/clash",
                "postgres", "Lsg-1234");

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM cards");

        while (rs.next()) {
            System.out.println("Player ID: " + rs.getString("player_id") + " " + "Level: " + " " + rs.getString("level ") + "Rarity: " + " " + rs.getString("rarity ") + "Name: " + " " + rs.getString("name") + " " + (rs.getString("id") + " " + "Id: "));

            Statement stmt2 = connection.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM cards WHERE player_id=" + rs.getString("id"));
            while (rs2.next()) {
                System.out.println("Cards");
                System.out.println("Card ID: " + rs2.getString("id") + " " + "Player ID: " + rs2.getString("player_id") + " " + "Name: " + rs2.getString("name") + " " + "Level: " + rs2.getString("level ") + " " + "Rarity: " + rs2.getString("rarity "));
            }
        }
    }

    private static void Exercise2() throws SQLException {
        // Connect to the database
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/clash",
                "postgres", "Lsg-1234");
        // Connect to the server to insert the values we want to create our cards    
        PreparedStatement insertPlayer = connection.prepareStatement("INSERT INTO cards (name, level , id, rarity ,  player_id, ¡) VALUES (?, ?, ?, ?, ?,)");
        Scanner input = new Scanner(System.in);

        // Save the data requested to the user
        System.out.println("Create a Name for a card: ");
        String name = input.nextLine();

        System.out.println("What level does it have? 1-14: ");
        String level  = input.nextLine();

        System.out.println("What player_id does it have? : ");
        String player_id = input.nextLine();

        System.out.println("What rarity does it have? : ");
        String rarity  = input.nextLine();
        System.out.println("Create an Id for a card: ");
        String id = input.nextLine();
     
        // Here we indicate which table to go to
        insertPlayer.setString(1, name);
        insertPlayer.setInt(2, Integer.parseInt(level ));
        insertPlayer.setInt(3, getLastId() + 1);
        insertPlayer.setInt(4, Integer.parseInt(rarity ));
        insertPlayer.setInt(5, Integer.parseInt(player_id));
        insertPlayer.executeUpdate();
    }

    private static int getLastId() throws SQLException {
        int id = 0;
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/clash",
                    "postgres", "Lsg-1234");

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id FROM cards order by id ASC");
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            System.err.print("SQL Exception: ");
            System.err.println(ex.getMessage());
        }
        return id;
    }

    private static void Exercise3() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("Error from postgradesql Driver" + ex);
        }
        Connection connection = null;

        connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/clash",
                "postgres", "Lsg-1234");

        Scanner input = new Scanner(System.in);

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM cards");

        while (rs.next()) {
            System.out.println("Name: " + rs.getString("name") + " Id: " + rs.getInt("id"));
        }

        // Ask for the data and save the data.
        System.out.println("Enter the Id of the card you want to modify ");
        String id = input.nextLine();

        System.out.println("Enter the Name of the card you want to modify ");
        String name = input.nextLine();

        System.out.println("Enter the Level of the card you want to modify: ");
        String level = input.nextLine();

        System.out.println("Enter the player_id of the card you want to modify : ");
        String player_id = input.nextLine();

        System.out.println("Enter the Rarity of the card you want to modify : ");
        String rarity  = input.nextLine();

        System.out.println("Cards");

        PreparedStatement updatePlayer = connection.prepareStatement("update cards SET name='" + name + "',level=" + level  + ",player_id=" + player_id +  ",rarity =" + rarity  +  " where id=" + id);
        updatePlayer.executeUpdate();

    }

    private static void Exercise4() throws SQLException {
        Connection connection = null;
// Database connect
        connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/clash",
                "postgres", "Lsg-1234");
        // Ask the user and insert data into the cards table
        PreparedStatement insertPartida = connection.prepareStatement("INSERT INTO cards(player_id, name, id, level , rarity ) VALUES (?, ?, ?, ?, ?)");
         Scanner input = new Scanner(System.in);
    System.out.println("Insert the id of the card:");
    String id = input.nextLine();
    System.out.println("Insert the name of the card to change it:");
    String name = input.nextLine();
    System.out.println("Change the rarity of the card:");
    String rarity  = input.nextLine();
    System.out.println("What level does the card have?");
    String level   = input.nextLine();
    System.out.println("What player_id does the card have?");
    String player_id = input.nextLine();

    insertPartida.setInt(1, Integer.parseInt(id));
    insertPartida.setInt(2, Integer.parseInt(name));
    insertPartida.setInt(3, getLastIdPart() + 1);
    insertPartida.setString(4, rarity );
    insertPartida.setString(5, level  );
    insertPartida.setString(6, player_id);
    insertPartida.executeUpdate();

}

  private static int getLastIdPart() throws SQLException {
    int id = 0;

    try {
        Connection connection = null;
        // Database connect
        connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/clash",
                "postgres", "Lsg-1234");
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id FROM cards order by id ASC");
        while (rs.next()) {
            id = rs.getInt("id");
        }
                
    } catch (SQLException ex) {
        System.err.print("SQL Exception: ");
        System.err.println(ex.getMessage());
    }
    return id;

   
   }
}