
import java.util.Scanner;
import java.util.HashMap;

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

      System.out.println("Enter Quantity:")
      quantity = scan.nextInt();
    }

  }


}
