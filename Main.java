import java.io.*;
import java.util.*;

import Customer.Customer;
import Exceptions.*;
import Validation.RegisterValidation;

public class Main 
{
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "adminpass";
    private static final String CSV_FILE = "Data/users.csv";

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> users = loadUsersFromCSV();
        boolean flag=true;
        
        while (flag) 
        {
            try
            {    
                System.out.println("\n\t\tWelcome to the Car Dealership Management System");
                System.out.println();
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                System.out.println();
                System.out.print("Please choose an option: ");

                int option = scanner.nextInt();
                scanner.nextLine(); 

                switch (option) {

                    case 1:
                        System.out.print("Enter your Username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter your Password: ");
                        String password = scanner.nextLine();
                        System.out.println();

                        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) 
                        {
                            System.out.println("Admin logged in Successfully.");
                            System.out.println();
                            System.out.println("\t\tWelcome Admin");
                            Dealership.main(args);
                            // Admin functionality here
                        } 
                        else if (users.containsKey(username) && users.get(username).equals(password)) 
                        {
                            System.out.println("Customer logged in successfully.");
                            System.out.println();
                            System.out.println("\t\tWelcome Customer - "+username);
                            Customer c = new Customer(username, password);
                            Customer.main(args);
                            // Customer functionality here
                        } 
                        else 
                        {
                            try
                            {
                                throw new InvalidUserException("Invalid username or password.");
                            }
                            catch(InvalidUserException e)
                            {
                                System.out.println(e);
                            }
                        }
                        break;

                    case 2:
                        String newUser = UserValidator();
                        String newPassword = PasswordValidator();
                        System.out.println();
                        if (users.containsKey(newUser)) 
                        {
                            try
                            {
                                throw new UserAlreadyExistsException("Username already exists. Please try again.");
                            }
                            catch(UserAlreadyExistsException e)
                            {
                                System.out.println(e);
                            }
                        }
                        else if(users.containsValue(newPassword))
                        {
                            try
                            {
                                throw new PasswordAlreadyTakenException("Password already taken by another user. Please try again.");
                            }
                            catch(PasswordAlreadyTakenException e)
                            {
                                System.out.println(e);
                            }
                        }
                        else 
                        {
                            users.put(newUser, newPassword);
                            saveUsersToCSV(users);
                            System.out.println("Registration successful.");
                        }
                        break;

                    case 3:
                        System.out.println("Exiting the system. Goodbye!");
                        flag=false;
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
    
    private static Map<String, String> loadUsersFromCSV() 
    {
        Map<String, String> users = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] user = line.split(",");
                users.put(user[0], user[1]);
            }
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred while loading user data.");
        }
        return users;
    }

    private static void saveUsersToCSV(Map<String, String> users) 
    {
        try (PrintWriter pw = new PrintWriter(new FileWriter(CSV_FILE))) 
        {
            for (Map.Entry<String, String> user : users.entrySet()) 
            {
                pw.println(user.getKey() + "," + user.getValue());
            }
        } 
        catch (IOException e) 
        {
            System.out.println("An error occurred while saving user data.");
        }
    }

    public static String UserValidator()
    {
        Scanner scanner = new Scanner(System.in);
        String newUser;
        boolean user_valid=false;
        do
        {
            System.out.print("Choose a username: ");
            newUser = scanner.nextLine();
            if(newUser.length()>=8)
            {
                user_valid=true;
            }
            else
            {
            System.out.println("Username should be at least 8 characters long.");         
            }

        }while(!user_valid);

        return newUser;
    }
    public static String PasswordValidator()
    {
        Scanner scanner = new Scanner(System.in);
        String newPassword;
        boolean password_valid=false;
        do {
            System.out.print("Enter password: ");
            newPassword = scanner.nextLine();
            if (RegisterValidation.isValidPassword(newPassword)) 
            {
                System.out.print("Confirm password: ");
                String confirmPassword = scanner.nextLine();
                if (!newPassword.equals(confirmPassword)) 
                {
                    System.out.println("Passwords do not match. Please try again.");
                } 
                else 
                {
                    password_valid = true;
                }

            } 
            else 
            {
                System.out.println("Password should be at least 8 characters long and should contains atleast 1 Uppercase Alphabet, atleast 1 Lowercase Alphabet,atleast 1 Digit and atleast 1 Special Character.");
                System.out.println("Please try again.");
            }
        } while (!password_valid);

        return newPassword;
    }

}
