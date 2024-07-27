package Customer;

import Inventory.*;
import Search.*;
import java.io.*;
import java.util.*;
// import java.util.stream.Collectors;
import Exceptions.*;

public class Customer 
{
    static String username;
    static String password;
    public static List<CarImpl> cars;

    Scanner scanner = new Scanner(System.in);
    static String car_buy_csv_path="Data/customer_buy.csv";
    static String cars_csv_path="Data/cars.csv";
    
    public Customer(String username, String password) 
    {
        Customer.username = username;
        Customer.password = password;
        Customer.cars = loadCarsFromCSV(cars_csv_path);
    }

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) 
        {
            try
            {
                System.out.println(
                        "\n1. Display all Car details\n2. Search a Car\n3. Buy a Car\n4. History of Purchased cars\n5. Exit");
                System.out.println();
                System.out.println("Enter your choice: ");

                int choice = scanner.nextInt();
                switch (choice) 
                {
                    case 1:
                        System.out.println();
                        System.out.println("All Car Details");
                        System.out.println();
                        readCars();
                        break;

                    case 2:
                        boolean count = true;
                        while (count) 
                        {
                            try
                            {
                                System.out.println("\n1. Search by Car price\n2. Search by Car brand\n3. Exit");
                                System.out.println();
                                System.out.println("Enter your choice");
                                int c = scanner.nextInt();
                                switch (c) 
                                {
                                    case 1:
                                        System.out.println();
                                        System.out.println("Enter the min price:");
                                        int minPrice = scanner.nextInt();
                                        System.out.println("Enter the max price:");
                                        int maxPrice = scanner.nextInt();
                                        System.out.println();
                                        searchCarsByPrice(minPrice, maxPrice);
                                        break;

                                    case 2:
                                        System.out.println();
                                        System.out.println("Enter the Car brand name:");
                                        String brand = scanner.next();
                                        System.out.println();
                                        searchCarsByBrand(brand);
                                        break;

                                    case 3:
                                        count = false;
                                        System.out.println("Exiting...");
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
                        break;

                    case 3:
                        System.out.println();
                        System.out.println("Enter Id of Car:");
                        String id = scanner.next();
                        buyCar(id);
                        break;

                    case 4:
                        System.out.println();
                        System.out.println("History of Purchased cars");
                        System.out.println();
                        buyHistory();
                        break;

                    case 5:
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

    public static void readCars() 
    {
        for (CarImpl car : cars) 
        {
            System.out.println(car.getCarDetails());
        }
    }

    // Method 2: Search using price, brand from the cars.csv
    public static void searchCarsByPrice(int minPrice, int maxPrice) 
    {
        Search s = new Search();
        s.searchByPrice(minPrice, maxPrice);
    }

    public static void searchCarsByBrand(String brand) 
    {
        Search s = new Search();
        s.searchByBrand(brand);
    }

    // Method 1: User details input
    public static void inputUserDetails(CarImpl car) 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your address: ");
        String address = scanner.nextLine();

        recordPurchase(name, username, address, car);
    }

    private static void recordPurchase(String name, String username, String address, CarImpl car) 
    {
        try (PrintWriter pw = new PrintWriter(new FileWriter(car_buy_csv_path, true))) 
        {
            pw.println(username + "," + name + "," + address + "," + car.getId() + "," + car.getName() + "," + car.getPrice());
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    // Method 3: Customer buys the car from the csv
    public static void buyCar(String id) 
    {
        try
        {
            Optional<CarImpl> carOptional = cars.stream()
                    .filter(car -> car.getId().equalsIgnoreCase(id))
                    .findFirst();

            if (carOptional.isPresent()) 
            {
                CarImpl car = carOptional.get();
                cars.remove(car);
                saveCarsToCSV(cars, cars_csv_path);
                // recordPurchase(car);
                inputUserDetails(car);
            } 
            else 
            {
                throw new CarsNotFoundException("Car details does not exist");
            }
        }
        catch(CarsNotFoundException e)
        {
            System.out.println(e);
        }
    }

    public static void buyHistory()
    {
        List<String> purchaseHistory = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(car_buy_csv_path))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] data = line.split(",");
                // Assuming the first element is the customer ID
                if (data[0].equals(username)) 
                {
                    purchaseHistory.add("Username: "+data[0]+", Name: "+data[1]+", Address: "+data[2]+", Car id: "+data[3]+", Car name: "+data[4]+", Car Price: "+data[5]);
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred while reading the purchase history.");
            e.printStackTrace();
        }

        if (purchaseHistory.isEmpty()) 
        {
            System.out.println("No purchase history found for customer username: " + username);
            System.out.println();
        } 
        else 
        {
            System.out.println("Purchase history for customer username: " + username);
            System.out.println();
            for(String record : purchaseHistory) 
            {
                System.out.println(record);
            }
        }
    }

    private static List<CarImpl> loadCarsFromCSV(String csvFile) 
    {
        List<CarImpl> cars = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] carDetails = line.split(",");
                cars.add(new CarImpl(carDetails[0], carDetails[1], carDetails[2], carDetails[3], carDetails[4],
                        carDetails[5], Integer.parseInt(carDetails[6]), Integer.parseInt(carDetails[7]),
                        carDetails[8], carDetails[9], carDetails[10], carDetails[11]));
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return cars;
    }

    private static void saveCarsToCSV(List<CarImpl> cars, String csvFile) 
    {
        try (PrintWriter pw = new PrintWriter(new FileWriter(csvFile))) 
        {
            for (CarImpl car : cars) 
            {
                pw.println(car.id + "," + car.name + "," + car.brand + "," + car.makeYear + "," + car.modelName + ","
                        + car.color + "," + car.price + ","
                        + car.numberOfSeats + "," + car.fuelType + "," + car.airbag + "," + car.mileage + ","
                        + car.engine);
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }
}