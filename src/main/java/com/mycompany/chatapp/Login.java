/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Chatapp;

/**
 *
 * @author lab_services_student
 */
public class Login {
    private String registeredUsername;
    private String registeredPassword; 
    
    public boolean checkUsername(String username) {
        // Check if username contains an underscore and is no more than five characters long
        return username != null && username.contains("_") && username.length() <= 5;
    }
     
    public boolean checkPasswordComplexity(String password) {
        // Check if password is null or less than 8 characters
        // This code is from Copilot ai
        /* Prompt : Java code to check password complexity.
        Use boolean variables to check if password has a capital letter,
        contains a number, and has a special character.
        */
        if (password == null || password.length() < 8) { 
            return false;
        }
        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");
        
        return hasUpperCase && hasNumber && hasSpecialChar;
    }  
   
    public boolean checkCellphoneNumber(String phoneNumber) {
        // Check if the cellphone number matches the expected format
        String regex = "^\\+27\\d{9}$"; // +27 followed by 9 digits
        return phoneNumber != null && phoneNumber.matches(regex);
    }
     
    public String registerUser (String username, String password) {
        boolean isUsernameValid = checkUsername(username);
        boolean isPasswordValid = checkPasswordComplexity(password);

        if (!isUsernameValid) {
            return "Username is incorrectly formatted.";
        } 
        if (!isPasswordValid) {
            return "The password does not meet the complexity requirements.";
        } 

        // If both conditions are met
        this.registeredUsername = username;
        this.registeredPassword = password;            
        return "User has been registered successfully."; // Corrected message
    }

    public boolean loginUser (String username, String password) {
        return username.equals(registeredUsername) && password.equals(registeredPassword);
    } 
    
    public String returnLoginStatus(String username, String password) {
        if (loginUser (username, password)) {
            return "Login successful!";
        } else {
            return "Login failed. Please check your username and password. Register if you are a new user.";
        }
    }
}