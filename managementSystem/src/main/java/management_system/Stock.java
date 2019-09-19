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

  }
