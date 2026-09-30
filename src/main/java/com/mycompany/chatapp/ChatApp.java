/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.Chatapp;

import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 */
public class ChatApp {
    private static String username;
    private static String password;
    private static String phoneNumber;
    private static Login loginSystem = new Login();

    // Part 2: Added Functionality
    private static boolean isLoggedIn = false;
    private static String messages = "";  // Single String holding all messages 
    private static int maxMessages;
    private static int messagesEntered = 0;  // Track number of messages entered

    public static void main(String[] args) {
        showInitialChoice();
    }

    private static void showInitialChoice() {
        String choice = JOptionPane.showInputDialog(null, "Welcome To Chat App, would you like to Register or Login? (Enter 'register' or 'login' or 'exit' to quit)").toLowerCase();

        if (choice.equals("register")) {
            registerUser ();
        } else if (choice.equals("login")) {
            loginUser ();
        } else if (choice.equals("exit")) {
            JOptionPane.showMessageDialog(null, "Exiting the application. Goodbye!");
        } else {
            JOptionPane.showMessageDialog(null, "Invalid choice. Please enter 'register', 'login', or 'exit'.");
            showInitialChoice();
        }
    }

    private static void registerUser () {
        username = JOptionPane.showInputDialog(null, "Enter your username:");
        if (validateUsername(username)) {
            JOptionPane.showMessageDialog(null, "Username successfully captured.");
        } else {
            JOptionPane.showMessageDialog(null, "Username is not correctly formatted. Please ensure that your username contains an underscore and is no more than five characters long.");
            username = JOptionPane.showInputDialog(null, "Enter your username:");
            return;
        }

        password = JOptionPane.showInputDialog(null, "Enter your password:");
        if (validatePassword(password)) {
            JOptionPane.showMessageDialog(null, "Password successfully captured.");
        } else {
            JOptionPane.showMessageDialog(null, "Password is not correctly formatted. Please ensure your password has at least eight characters, a capital letter, a number, and a special character.");
            password = JOptionPane.showInputDialog(null, "Enter your password:");
            return;
        }

        phoneNumber = JOptionPane.showInputDialog(null, "Enter your phone number:");
        if (validatePhoneNumber(phoneNumber)) {
            JOptionPane.showMessageDialog(null, "Cell phone number successfully added.");
        } else {
            JOptionPane.showMessageDialog(null, "Cell phone number incorrectly formatted or does not contain international code.");
            phoneNumber = JOptionPane.showInputDialog(null, "Enter your phone number:");
            return;
        }

        // Register the user
        String registrationMessage = loginSystem.registerUser (username, password);
        JOptionPane.showMessageDialog(null, registrationMessage);
        showInitialChoice();
    }

    private static void loginUser () {
        username = JOptionPane.showInputDialog(null, "Enter your username:");
        password = JOptionPane.showInputDialog(null, "Enter your password:");

        String loginStatus = loginSystem.returnLoginStatus(username, password);
        JOptionPane.showMessageDialog(null, loginStatus);

        if (loginStatus.equals("Login successful!")) {
            isLoggedIn = true;
            setMaxMessages();
            showMainMenu();
        } else {
            showInitialChoice();
        }
    }

    // Prompt user to define how many messages they want to enter
    private static void setMaxMessages() {
        String input = JOptionPane.showInputDialog(null, "How many messages would you like to enter?");
        try {
            maxMessages = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number. Setting to 5 messages by default.");
            maxMessages = 5;
        }
        messages = "";  // Reset messages
        messagesEntered = 0;
    }

    // Show numeric menu until user chooses to quit
    private static void showMainMenu() {
        while (isLoggedIn) {
            StringBuilder menuBuilder = new StringBuilder();
            menuBuilder.append("Welcome to QuickChat.\n");
            menuBuilder.append("Choose an option:\n");
            menuBuilder.append("1) Send Messages\n");
            menuBuilder.append("2) Show recently sent messages (Coming soon)\n");
            menuBuilder.append("3) Quit");

            String choice = JOptionPane.showInputDialog(null, menuBuilder.toString());

            switch (choice) {
                case "1":
                    sendMessages();
                    break;
                case "2":
                    JOptionPane.showMessageDialog(null, "Coming soon.");
                    break;
                case "3":
                    isLoggedIn = false;
                    JOptionPane.showMessageDialog(null, "Exiting the application. Goodbye!");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please enter 1, 2, or 3.");
            }
        }
    }

    // Allow user to enter only the set number of messages, store in single String with newlines
    private static void sendMessages() {
        while (messagesEntered < maxMessages) {
            String message = JOptionPane.showInputDialog(null,
                    "Enter your message (" + (messagesEntered + 1) + "/" + maxMessages + "):");
            if (message != null && !message.trim().isEmpty()) {
                messages += message + "\n";  // Append message with newline
                messagesEntered++;
            } else {
                JOptionPane.showMessageDialog(null, "Message cannot be empty. Please try again.");
            }
        }
        JOptionPane.showMessageDialog(null, "Messages sent successfully!");
    }

    private static boolean validateUsername(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    private static boolean validatePassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasNumber = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

        return hasUpperCase && hasNumber && hasSpecialChar;
    }

    // Chat GPT code
    // Prompt : Can you give me Java code for a regex cell phone checker
    private static boolean validatePhoneNumber(String phoneNumber) {
        String regex = "^\\+27\\d{9}$";
        return phoneNumber != null && phoneNumber.matches(regex);
        /* The above code will check whether the user input matches the requirements
           needed to be a South African phone number  
        */
    }
}
