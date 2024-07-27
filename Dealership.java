import java.util.Scanner;

import Inventory.*;
import Search.*;
import Sorting.*;
import Exceptions.*;

public class Dealership 
{
    public static void main(String[] args) 
    {

        // Create an Inventory object with a predefined size
        Inventory inventory = new Inventory();

        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) 
        {
            try
            {
                System.out.println(
                        "\n1. Create Car details\n2. Read Car details\n3. Delete Car details\n4. Update Car details\n5. Search for Car\n6. Sort the Car\n7. Sales report\n8. Exit from Program");
                System.out.println();
                System.out.println("Enter your choice: ");

                int choice = sc.nextInt();
                switch (choice) 
                {
                    case 1:
                        System.out.println();
                        System.out.println("Enter Car name:");
                        String name = sc.next();
                        System.out.println("Enter Car brand:");
                        String brand = sc.next();
                        System.out.println("Enter Car make year:");
                        String year = sc.next();
                        sc.nextLine();
                        System.out.println("Enter Car model name:");
                        String model_name = sc.nextLine();
                        System.out.println("Enter Car color:");
                        String color = sc.next();
                        System.out.println("Enter Car price:");
                        int price = sc.nextInt();
                        sc.nextLine();
                        System.out.println("Enter Car engine:");
                        String engine = sc.nextLine();
                        System.out.println("Enter No of seats:");
                        int seats = sc.nextInt();
                        System.out.println("Enter Car fuel type:");
                        String fuel = sc.next();
                        System.out.println("Enter No of airbags:");
                        String airbag = sc.next();
                        sc.nextLine();
                        System.out.println("Enter Car mileage:");
                        String mileage = sc.nextLine();
                        System.out.println();
                        // sc.nextLine();
                        inventory.createCar(name, brand, year, model_name, color, price, seats, fuel, airbag, mileage,
                                engine);
                        // inventory.createCar("Model S", "Tesla", 2021, "Model S Plaid", "Red");
                        break;

                    case 2:
                        System.out.println();
                        System.out.println("Displaying Car details");
                        inventory.readCars();
                        break;

                    case 3:
                        // Delete a car by its name
                        System.out.println("Enter Car ID to delete Car details:");
                        String delete_id = sc.next();
                        System.out.println();
                        inventory.deleteCar(delete_id);
                        break;

                    case 4:
                        // Update the specifications of a Car
                        System.out.println("Enter the Car ID to Update Car details");
                        String update_id = sc.next();
                        System.out.println();
                        inventory.updateCar(update_id);
                        // inventory.updateCar("Civic", "Honda", 2022, "Civic LA", "Black");
                        break;

                    case 5:
                        Search search = new Search();
                        search.search();
                        break;

                    case 6:
                        Sorting sorting = new Sorting();
                        sorting.sorting();
                        break;

                    case 7:
                        System.out.println();
                        System.out.println("Sales Report: ");
                        System.out.println();
                        inventory.salesReport();
                        break;

                    case 8:
                        flag = false;
                        System.out.println("Exiting...");
                        System.out.println();
                        break;

                    default:
                        throw new InvalidChoiceException("Invalid option. Please try again.");
                        
                }
            }
            catch(InvalidChoiceException e)
            {
                System.out.println(e);
            }
        }
    }
}