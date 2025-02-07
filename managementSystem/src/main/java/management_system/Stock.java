package management_system;

import java.sql.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.List;


public class Stock {

  private static Connection connect = null;

  public static List<String> clinicsLow = new ArrayList<>();

  public static void main(String [] args) {

    Scanner scan = new Scanner(System.in);
    int option = 0;


    while(true){
      initConnectionToDB();
      retrieveListOfLow();
      System.out.println("For Inserting Enter 1, For Updating Enter 2, For Retrieving Enter 3");
      option = scan.nextInt();
      getInput(option);

    }


  }

  public static void getInput(int n) {

    String clinicName = null;
    String medicine = null;
    int quantity = 0;
    Scanner scan = new Scanner(System.in);

    switch(n) {
      case 1:
        System.out.println("Enter Clinic Name:");
        clinicName = scan.nextLine();

        System.out.println("Enter Medication Name:");
        medicine = scan.nextLine();

        System.out.println("Enter Quantity:");
        quantity = scan.nextInt();

        insertData(clinicName,medicine,quantity);
        break;

      case 2:
        System.out.println("Enter Clinic Name:");
        clinicName = scan.nextLine();

        System.out.println("Enter Medication Name:");
        medicine = scan.nextLine();

        System.out.println("Enter Quantity:");
        quantity = scan.nextInt();

        updateData(clinicName, medicine,quantity);
        break;

      case 3:

        System.out.println("Enter Clinic Name:");
        clinicName = scan.nextLine();

        retrieveDataPerClinic(clinicName);
        break;

     default:
       System.out.println("Something went wrong :( ");
       break;




    }

  }

    public static void initConnectionToDB() {

    //  Connection connect = null;

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

    //Connection connect = null;
    Statement statement = null;

    try {
      Class.forName("org.sqlite.JDBC");
      connect = DriverManager.getConnection("jdbc:sqlite:SqliteJavaDB.db");
      statement = connect.createStatement();
      String querie = " Create TABLE ClinicStock" + "(c_id INTEGER PRIMARY KEY AUTOINCREMENT," + "c_name TEXT NOT NULL, " + "Nevirapine INTEGER, " + "Stavudine INTEGER, " + "Zidotabine INTEGER) ";

      statement.executeUpdate(querie);
      statement.close();
      connect.close();

    } catch(Exception e ){
      System.out.println(e.getMessage());

    }
  System.out.println("Table Created");

  }
  public static void insertData(String clinic, String medicine, int quantity) {

    Statement statement = null;

    System.out.println("LINE_____126" + medicine);

    String querie = "";

    try {
      statement = connect.createStatement();
         if(medicine.equals("Nevirapine")){
            querie = "INSERT INTO ClinicStock(c_name,Nevirapine) " + " VALUES('" +clinic+ "'," + quantity + ")";
            statement.executeUpdate(querie);

          } else if(medicine.equals("Stavudine")) {

            querie = "INSERT INTO ClinicStock (c_name,Stavudine) " +" VALUES ('"+clinic+"', " +quantity+")";
            statement.executeUpdate(querie);
            System.out.println("LINE_____140");

          } else if(medicine.equals("Zidotabine")) {

            querie = "INSERT INTO ClinicStock (c_name,Stavudine) " +"VALUES ('"+clinic+"', " +quantity+")";
            statement.executeUpdate(querie);
            System.out.println("LINE_____146");

          } else {

            System.out.println("Retry sorry :( ");

        }

        System.out.println("Inserted Data");
        statement.close();
        connect.commit();

    } catch(Exception e) {
        System.out.println(e.getMessage());

    }

  }
  public static void updateData(String clinic, String medicine, int quantity) {
    Statement statement = null;

    String querie = "";

    try {
      statement = connect.createStatement();
        switch(medicine) {

          case "Nevirapine":

            querie = "UPDATE ClinicStock SET c_name = '" +clinic+ "', Nevirapine =" + quantity;
            statement.executeUpdate(querie);
            break;

          case "Stavudine":

            querie = "UPDATE ClinicStock SET c_name = '" +clinic+ "', Stavudine =" + quantity;
            statement.executeUpdate(querie);
            break;

          case "Zidotabine":

            querie = "UPDATE ClinicStock SET c_name = '" +clinic+ "', Zidotabine =" + quantity;
            statement.executeUpdate(querie);
            break;

          default:
            System.out.println("Retry Sorry");
            break;

        }

        System.out.println("Updated Data");
        statement.close();
        connect.commit();

    } catch(Exception e) {
        System.out.println(e.getMessage());

    }

  }

  public static void retrieveDataPerClinic(String clinic) {
    PreparedStatement statement = null;

    int nev = 0;
    int stavu = 0;
    int zid = 0;


    try {
        String querie = "SELECT * FROM ClinicStock WHERE c_name = ?";
        statement = connect.prepareStatement(querie);
        statement.setString(1, clinic);
        ResultSet rs = statement.executeQuery();

        while( rs.next()) {
          nev = rs.getInt("Nevirapine");
          stavu = rs.getInt("Stavudine");
          zid = rs.getInt("Zidotabine");
          System.out.println("Data for Clinic: " +clinic+ "\n" +"Nevirapine: "+nev+ "\n"+ "Stavudine: " +stavu+ "\n" + "Zidotabine: " +zid);
        }
        rs.close();
    }catch (Exception e) {
      System.out.println(e.getMessage() + "--------------line234");
    }

  }

  public static String retrieveListOfLow() {

    PreparedStatement statement = null;
    String name = null;
    String list = "";

    try {
      String querie = "SELECT * FROM ClinicStock WHERE Nevirapine < 5 OR Stavudine < 5 OR Zidotabine < 5;";
      statement = connect.prepareStatement(querie);
      ResultSet rs = statement.executeQuery();
      System.out.println("------------------line247");

      while(rs.next()) {
        name = rs.getString("c_name");
        clinicsLow.add(name);
        list = String.join("\n",clinicsLow);
        System.out.println("These Clinics are low on medication:" + name + "\n");
      }
      rs.close();

    }catch (Exception e) {
      System.out.println(e.getMessage() + "------------------line255");
    }
    return list;

  }
  public static void displayWarning() {

  }
  }
