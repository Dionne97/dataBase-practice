package management_system;

import java.sql.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Stock {

  private static HashMap<String, String> clinics = new HashMap<String, String>();

  public static void main(String [] args) {

    getInput();

  }

  public static void getInput() {

    String clinicName = null;
    String medicine = null;
    int quantity = 0;
    Scanner scan = new Scanner(System.in);

    initConnectionToDB();
    tableCreation();

    while (true) {
      System.out.println("Enter Clinic Name:");
      clinicName = scan.nextLine();

      System.out.println("Enter Medication Name:");
      medicine = scan.nextLine();

      System.out.println("Enter Quantity:");
      quantity = scan.nextInt();
    }
  }

    public static void initConnectionToDB() {

      Connection connect = null;

      try {
        Class.forName("org.sqlite.JDBC");
        connect = DriverManager.getConnection("jdbc:sqlite:SqliteJavaDB.db");

      }catch (Exception e ) {
        System.out.println( "Failed to create database-----" + e.getMessage());
        //System.exit(0);
      }

      System.out.println("Database Created");
    }

  public static void tableCreation() {

    Connection connect = null;
    Statement statement = null;

    try {
      Class.forName("org.sqlite.JDBC");
      connect = DriverManager.getConnection("jdbc:sqlite:SqliteJavaDB.db");
      statement = connect.createStatement();
      String querie = " Create TABLE ClinicStock" + "(c_id INTEGER PRIMARY KEY AUTOINCREMENT," + "c_name TEXT NOT NULL, " + "Nevirapine INTEGER, " + "Stavudine INTEGER, " + "Zidovine INTEGER) ";

      statement.executeUpdate(querie);
      statement.close();
      connect.close();

    } catch(Exception e ){
      System.out.println(e.getMessage()+);

    }
  System.out.println("Table Created");

  }
  public static void addingDatatoDB(String clinic, String medicine, int quantity) {

  }

  public static void retrieveListOfLow() {

  }
  public static void displayWarning() {

  }
  }
