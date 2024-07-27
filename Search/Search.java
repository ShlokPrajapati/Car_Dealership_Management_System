package Search;

import java.util.Scanner;
import java.util.List;
import Inventory.*;
import java.util.stream.Collectors;
import Exceptions.*;

interface Searchable 
{
    void searchByName(String name);
    void searchByBrand(String brand);
    void searchByPrice(int minPrice, int maxPrice);
}

public class Search implements Searchable 
{
    Inventory it = new Inventory();
    public List<CarImpl> cars = it.cars;
    Scanner scanner = new Scanner(System.in);

    public void search() {
        boolean flag = true;
        while (flag) 
        {
            try
            {
                System.out.println("\n1. Search by car name\n2. Search by car brand\n3. Search by price\n4. Exit");
                System.out.println();
                System.out.println("Enter your choice:");
                int searchType = scanner.nextInt();
                switch (searchType) 
                {
                    case 1:
                        System.out.println();
                        System.out.println("Enter car name:");
                        String name = scanner.next();
                        System.out.println();
                        searchByName(name);
                        break;

                    case 2:
                        System.out.println();
                        System.out.println("Enter car brand:");
                        String brand = scanner.next();
                        System.out.println();
                        searchByBrand(brand);
                        break;

                    case 3:
                        System.out.println();
                        System.out.println("Enter the min price:");
                        int minPrice = scanner.nextInt();
                        System.out.println("Enter the max price:");
                        int maxPrice = scanner.nextInt();
                        System.out.println();
                        searchByPrice(minPrice, maxPrice);
                        break;

                    case 4:
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

    public void searchByName(String name) 
    {
        List<CarImpl> result = cars.stream()
                .filter(car -> car.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());

        if(result.size() <= 0)
        {
            System.out.println("No Results found");
            System.out.println();
        }
        else
        {
            for (CarImpl car : result) {
                System.out.println(car.getCarDetails());
            }
        }
    }

    public void searchByBrand(String brand) 
    {
        List<CarImpl> result = cars.stream()
                .filter(car -> car.getBrand().equalsIgnoreCase(brand))
                .collect(Collectors.toList());

        if(result.size() <= 0)
        {
            System.out.println("No Results found");
            System.out.println();
        }
        else
        {
            for (CarImpl car : result) {
                System.out.println(car.getCarDetails());
            }
        }

    }

    public void searchByPrice(int minPrice, int maxPrice) 
    {
        List<CarImpl> result = cars.stream()
            .filter(car -> car.getPrice() >= minPrice && car.getPrice() <= maxPrice)
                .collect(Collectors.toList());

        if(result.size() <= 0)
        {
            System.out.println("No Results found");
            System.out.println();
        }
        else
        {
            for (CarImpl car : result) {
                System.out.println(car.getCarDetails());
            }
        }
        
    }


}
