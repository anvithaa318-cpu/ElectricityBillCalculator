package electricityBillCalculator;

import java.util.ArrayList;
import java.util.Scanner;

class Consumer {

    int consumerNumber;
    String consumerName;
    int previousReading;
    int currentReading;
    int unitsConsumed;

    double energyCharge;
    double fixedCharge;
    double surcharge;
    double totalBill;

    Consumer(int consumerNumber, String consumerName,
             int previousReading, int currentReading) {

        this.consumerNumber = consumerNumber;
        this.consumerName = consumerName;
        this.previousReading = previousReading;
        this.currentReading = currentReading;

        calculateBill();
    }

    // Feature 2: Calculate units consumed
    void calculateBill() {

        unitsConsumed = currentReading - previousReading;

        // Feature 3: Calculate electricity charges
        if (unitsConsumed <= 100) {
            energyCharge = unitsConsumed * 1.50;
        }

        else if (unitsConsumed <= 200) {
            energyCharge = (100 * 1.50)
                         + (unitsConsumed - 100) * 2.50;
        }

        else if (unitsConsumed <= 500) {
            energyCharge = (100 * 1.50)
                         + (100 * 2.50)
                         + (unitsConsumed - 200) * 4.00;
        }

        else {
            energyCharge = (100 * 1.50)
                         + (100 * 2.50)
                         + (300 * 4.00)
                         + (unitsConsumed - 500) * 6.00;
        }

        // Feature 4: Fixed charges and surcharge
        fixedCharge = 50;

        surcharge = energyCharge * 0.05;

        totalBill = energyCharge + fixedCharge + surcharge;
    }

    // Feature 5: Display itemized electricity bill
    void displayBill() {

        System.out.println("\n====================================");
        System.out.println("       ELECTRICITY BILL");
        System.out.println("====================================");

        System.out.println("Consumer Number : " + consumerNumber);
        System.out.println("Consumer Name   : " + consumerName);

        System.out.println("Previous Reading: " + previousReading);
        System.out.println("Current Reading : " + currentReading);

        System.out.println("------------------------------------");

        System.out.println("Units Consumed  : " + unitsConsumed);

        System.out.printf("Energy Charges  : Rs. %.2f%n", energyCharge);

        System.out.printf("Fixed Charges   : Rs. %.2f%n", fixedCharge);

        System.out.printf("Surcharge (5%%)  : Rs. %.2f%n", surcharge);

        System.out.println("------------------------------------");

        System.out.printf("Total Bill      : Rs. %.2f%n", totalBill);

        System.out.println("====================================");
    }
}


public class ElectricityBillCalculator {

    static Scanner sc = new Scanner(System.in);

    // Feature 7: Maintain multiple consumer records
    static ArrayList<Consumer> consumers = new ArrayList<>();


    // Feature 1: Enter consumer details
    static void addConsumer() {

        System.out.println("\n--- ENTER CONSUMER DETAILS ---");

        System.out.print("Enter Consumer Number: ");
        int number = readInteger();

        // Check duplicate consumer number
        for (Consumer c : consumers) {

            if (c.consumerNumber == number) {

                System.out.println("Consumer number already exists!");
                return;
            }
        }

        System.out.print("Enter Consumer Name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Consumer name cannot be empty!");
            return;
        }

        System.out.print("Enter Previous Meter Reading: ");
        int previous = readInteger();

        System.out.print("Enter Current Meter Reading: ");
        int current = readInteger();

        // Feature 8: Validate meter readings
        if (previous < 0 || current < 0) {

            System.out.println("Error: Meter readings cannot be negative!");
            return;
        }

        if (current < previous) {

            System.out.println("Error: Current reading cannot be less than previous reading!");
            return;
        }

        // Create consumer object
        Consumer c = new Consumer(number, name, previous, current);

        consumers.add(c);

        System.out.println("\nConsumer record added successfully!");

        // Display generated bill
        c.displayBill();
    }


    // Feature 6: Search and display consumer bill
    static void searchConsumer() {

        System.out.print("\nEnter Consumer Number to Search: ");

        int number = readInteger();

        for (Consumer c : consumers) {

            if (c.consumerNumber == number) {

                System.out.println("\nConsumer Found!");

                c.displayBill();

                return;
            }
        }

        System.out.println("Consumer record not found!");
    }


    // Feature 7: Display all consumer records
    static void displayAllConsumers() {

        if (consumers.isEmpty()) {

            System.out.println("\nNo consumer records available!");

            return;
        }

        System.out.println("\n======= ALL CONSUMER RECORDS =======");

        for (Consumer c : consumers) {

            c.displayBill();
        }
    }


    // Input validation for integer values
    static int readInteger() {

        while (true) {

            try {

                int value = Integer.parseInt(sc.nextLine().trim());

                return value;

            } catch (NumberFormatException e) {

                System.out.print("Invalid input! Enter a valid integer: ");
            }
        }
    }


    // Main method
    public static void main(String[] args) {

        int choice;

        System.out.println("====================================");
        System.out.println("     ELECTRICITY BILL CALCULATOR");
        System.out.println("====================================");

        do {

            System.out.println("\n1. Add Consumer Details");
            System.out.println("2. Search Consumer Bill");
            System.out.println("3. Display All Consumer Bills");
            System.out.println("4. Exit");

            System.out.print("\nEnter Your Choice: ");

            choice = readInteger();

            switch (choice) {

                case 1:
                    addConsumer();
                    break;

                case 2:
                    searchConsumer();
                    break;

                case 3:
                    displayAllConsumers();
                    break;

                case 4:
                    System.out.println("\nThank you for using Electricity Bill Calculator!");
                    break;

                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}

