package Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterValidation 
{
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
    // Method to validate password length
    public static boolean isValidPassword(String password) 
    {
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return (password.length() >= 8 && matcher.matches()); // Password should be at least 8 characters long
    }
}