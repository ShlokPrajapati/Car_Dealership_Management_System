package Inventory;

import Exceptions.*;

abstract class Car 
{
    public String name;
    public String brand;
    public String makeYear;
    public String modelName;
    public String color;
    public String id;

    public int price;
    public int numberOfSeats;
    public String fuelType;
    public String airbag;
    public String mileage;
    public String engine;

    abstract String getName();
    abstract String getBrand();
    abstract String getYear();
    abstract int getPrice();
    abstract String getId();
    abstract String getCarDetails();

    abstract void updateSpecifications(String name, String brand, String makeYear, String modelName, String color,
            int price, int numberOfSeats, String fuelType, String airbag, String mileage, String engine);
}

public class CarImpl extends Car 
{
    public CarImpl(String id, String name, String brand, String makeYear, String modelName, String color, int price,
            int numberOfSeats, String fuelType, String airbag, String mileage, String engine) 
    {
        this.name = name;
        this.brand = brand;
        this.makeYear = makeYear;
        this.modelName = modelName;
        this.color = color;
        this.id = id;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.airbag = airbag;
        this.fuelType = fuelType;
        this.mileage = mileage;
        this.engine = engine;
    }

    public String getName() 
    {
        return this.name;
    }

    public String getBrand() 
    {
        return this.brand;
    }

    public String getYear() 
    {
        return this.makeYear;
    }

    public int getPrice() 
    {
        return this.price;
    }
   
    public String getId() 
    {
        return this.id;
    }

    public String getCarDetails() 
    {
        return String.format(
                "ID: %s, Name: %s, Brand: %s, Year: %s, Model: %s, Color: %s, Price: %d, No of Seats: %d, Fuel type: %s, Airbag: %s, Mileage: %s, Engine: %s",
                id, name, brand, makeYear, modelName, color, price, numberOfSeats, fuelType, airbag, mileage, engine);
    }

    public void updateSpecifications(String name, String brand, String makeYear, String modelName, String color,
            int price, int numberOfSeats, String fuelType, String airbag, String mileage, String engine) 
    {
        this.name = name;
        this.brand = brand;
        this.makeYear = makeYear;
        this.modelName = modelName;
        this.color = color;
        this.price = price;
        this.numberOfSeats = numberOfSeats;
        this.fuelType = fuelType;
        this.airbag = airbag;
        this.mileage = mileage;
        this.engine = engine;
    }
}