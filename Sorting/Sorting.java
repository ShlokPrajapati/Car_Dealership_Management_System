package Sorting;

import java.util.*;
import Inventory.*;
import Exceptions.*;

public class Sorting 
{
    Inventory it = new Inventory();
    public List<CarImpl> cars = it.cars;
    Scanner scanner = new Scanner(System.in);

    public void sorting() 
    {
        boolean flag = true;
        while (flag) 
        {
            try
            {
                System.out.println("\n1. Sort by Car make year\n2. Sort by Car price\n3. Exit");
                System.out.println();
                System.out.println("Enter your choice:");
                int searchType = scanner.nextInt();
                switch (searchType) 
                {
                    case 1:
                        System.out.println();
                        System.out.println("Sorting by Car make year");
                        sortByYear();
                        break;

                    case 2:
                        System.out.println();
                        System.out.println("Sorting by Car price");
                        sortByPrice();
                        break;

                    case 3:
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

    public void sortByYear() 
    {
        List<CarImpl> sortedCars = new ArrayList<>(cars);
        Collections.sort(sortedCars,
                (car1, car2) -> Integer.parseInt(car2.getYear()) - Integer.parseInt(car1.getYear()));

        for (CarImpl car : sortedCars) 
        {
            System.out.println(car.getCarDetails());
        }
        System.out.println();
    }

    public void sortByPrice() 
    {
        List<CarImpl> sortedCars = new ArrayList<>(cars);
        Collections.sort(sortedCars, Comparator.comparingInt(CarImpl::getPrice));

        for (CarImpl car : sortedCars) 
        {
            System.out.println(car.getCarDetails());
        }
        System.out.println();
    }
}
