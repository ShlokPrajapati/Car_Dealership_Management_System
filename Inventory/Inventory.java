package Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import Exceptions.*;

public class Inventory 
{

    Scanner sc = new Scanner(System.in);
    public List<CarImpl> cars;
    public String csvFilePath = "Data/cars.csv";
    public String cars_csv_sales = "Data/customer_buy.csv";

    public Inventory() 
    {
        this.cars = new ArrayList<>();
        loadCarsFromCSV();
    }

    private void loadCarsFromCSV() 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) 
        {
            String line;
            
            while ((line = br.readLine()) != null) 
            {
                String[] carDetails = line.split(",");
                
                if (carDetails.length == 12) 
                {
                    CarImpl car = new CarImpl(carDetails[0], carDetails[1], carDetails[2], carDetails[3], carDetails[4],
                            carDetails[5], Integer.parseInt(carDetails[6]), Integer.parseInt(carDetails[7]),
                            carDetails[8], carDetails[9], carDetails[10], carDetails[11]);
                    cars.add(car);
                }
            }
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
    }

    public void createCar(String name, String brand, String makeYear, String modelName, String color, int price,
            int numberOfSeats, String fuelType, String airbag, String mileage, String engine) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath, true))) 
        {
            String id = Integer.toString(cars.size() + 1);
            cars.add(new CarImpl(id, name, brand, makeYear, modelName, color, price, numberOfSeats, fuelType, airbag,
                    mileage, engine));
            bw.write(id + "," + name + "," + brand + "," + makeYear + "," + modelName + "," + color + "," + price + ","
                    + numberOfSeats + "," + fuelType + "," + airbag + "," + mileage + "," + engine);
            bw.newLine();
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
    }

    public void readCars() 
    {
        for (CarImpl car : cars) 
        {
            System.out.println(car.getCarDetails());
        }
    }

    public void updateCar(String id) 
    {
        for (CarImpl car : cars) 
        {
            if (car.getId().equalsIgnoreCase(id)) 
            {
                boolean flag = true;
                while (flag) 
                { 
                    try
                    {
                        System.out.println(
                                "\n1. To Update brand of Car\n2. To Update make year of Car\n3. To Update model name of Car\n4. To Update color of Car\n5. To Update price of Car\n6. To Update No of seats in Car\n7. To Update fuel type of Car\n8. To Update No of airbags in Car\n9. To Update mileage of Car\n10. To Update engine of Car\n11. Exit\n");
                        System.out.println();
                        System.out.println("Enter your choice:");
                        int c = sc.nextInt();
                        switch (c) 
                        {
                            case 1:
                                System.out.println();
                                System.out.println("Enter brand name of Car");
                                String update_brand = sc.next();
                                System.out.println();
                                car.updateSpecifications(car.name, update_brand, car.makeYear, car.modelName, car.color,
                                        car.price, car.numberOfSeats, car.fuelType, car.airbag, car.mileage, car.engine);
                                break;

                            case 2:
                                System.out.println();
                                System.out.println("Enter make year of Car");
                                String update_year = sc.next();
                                System.out.println();
                                car.updateSpecifications(car.name, car.brand, update_year, car.modelName, car.color,
                                        car.price,
                                        car.numberOfSeats, car.fuelType, car.airbag, car.mileage, car.engine);
                                break;

                            case 3:
                                System.out.println();
                                System.out.println("Enter model name of Car");
                                String update_model = sc.next();
                                System.out.println();
                                car.updateSpecifications(car.name, car.brand, car.makeYear, update_model, car.color,
                                        car.price,
                                        car.numberOfSeats, car.fuelType, car.airbag, car.mileage, car.engine);
                                break;

                            case 4:
                                System.out.println();
                                System.out.println("Enter color name of Car");
                                String update_color = sc.next();
                                System.out.println();
                                car.updateSpecifications(car.name, car.brand, car.makeYear, car.modelName, update_color,
                                        car.price, car.numberOfSeats, car.fuelType, car.airbag, car.mileage, car.engine);
                                break;

                            case 5:
                                System.out.println();
                                System.out.println("Enter price of Car");
                                int update_price = sc.nextInt();
                                System.out.println();
                                car.updateSpecifications(car.name, car.brand, car.makeYear, car.modelName, car.color,
                                        update_price, car.numberOfSeats, car.fuelType, car.airbag, car.mileage, car.engine);
                                break;

                            case 6:
                                System.out.println();
                                System.out.println("Enter No of seats in Car");
                                int update_seats = sc.nextInt();
                                System.out.println();
                                car.updateSpecifications(car.name, car.brand, car.makeYear, car.modelName, car.color,
                                        car.price,
                                        update_seats, car.fuelType, car.airbag, car.mileage, car.engine);
                                break;

                            case 7:
                                System.out.println();
                                System.out.println("Enter fuel type of Car");
                                String update_fuel = sc.next();
                                System.out.println();
                                car.updateSpecifications(car.name, car.brand, car.makeYear, car.modelName, car.color,
                                        car.price,
                                        car.numberOfSeats, update_fuel, car.airbag, car.mileage, car.engine);
                                break;

                            case 8:
                                System.out.println();
                                System.out.println("Enter No of airbags in Car");
                                String update_airbags = sc.next();
                                System.out.println();
                                car.updateSpecifications(car.name, car.brand, car.makeYear, car.modelName, car.color,
                                        car.price,
                                        car.numberOfSeats, car.fuelType, update_airbags, car.mileage, car.engine);
                                break;

                            case 9:
                                System.out.println();
                                System.out.println("Enter mileage of Car");
                                String update_mileage = sc.next();
                                System.out.println();
                                car.updateSpecifications(car.name, car.brand, car.makeYear, car.modelName, car.color,
                                        car.price,
                                        car.numberOfSeats, car.fuelType, car.airbag, update_mileage, car.engine);
                                break;

                            case 10:
                                System.out.println();
                                System.out.println("Enter engine of Car");
                                String update_engine = sc.next();
                                System.out.println();
                                car.updateSpecifications(car.name, car.brand, car.makeYear, car.modelName, car.color,
                                        car.price,
                                        car.numberOfSeats, car.fuelType, car.airbag, car.mileage, update_engine);
                                break;

                            case 11:
                                flag = false;
                                System.out.println("Exiting...");
                                System.out.println();
                                break;

                            default:
                                throw new InvalidChoiceException("Invalid option. Please try again.");
                        }
                        updateCSVFile();
                        System.out.println("Car details updated having Id: " + car.id);
                        System.out.println();
                        return;
                    }
                    catch(InvalidChoiceException e)
                    {
                        System.out.println(e);
                    }
                    
                }
            }
            
        }
        System.out.println("Car details does not exist");
        System.out.println();
    }

    public void deleteCar(String id) 
    {
        try
        {
            if (cars.removeIf(car -> car.id.equals(id))) 
            {
                System.out.println("Car details deleted having ID: " + id);
                System.out.println();
                updateCSVFile();
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

    public void updateCSVFile() 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath, false))) 
        {
            for (CarImpl car : cars) 
            {
                bw.write(car.id + "," + car.name + "," + car.brand + "," + car.makeYear + "," + car.modelName + "," + car.color + "," + car.price + ","
                + car.numberOfSeats + "," + car.fuelType + "," + car.airbag + "," + car.mileage + "," + car.engine);
                bw.newLine();
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void salesReport() 
    {
        try (BufferedReader br = new BufferedReader(new FileReader(cars_csv_sales))) 
        {
            String line;

            while ((line = br.readLine()) != null) 
            {
                String[] carDetails = line.split(",");
            
                System.out.println("Username: "+carDetails[0]+", Name: "+carDetails[1]+", Address: "+carDetails[2]+", Car id: "+carDetails[3]+", Car name: "+carDetails[4]+", Price: "+carDetails[5]);

            }
        } 
        catch (IOException e) 
        {
            System.out.println(e);
        }
    }

}
