/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.chatbot;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author RC_Student_Lab
 */
public class Chatbot {
     private static Login currentUser;
    private static boolean isLoggedIn = false;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Display welcome message for Part 2
        System.out.println("=== Welcome to QuickChat ===");
        
        // Registration and Login (Part 1 functionality)
        handleAuthentication(scanner);
        
        // Part 2: If login successful, show chat menu
        if (isLoggedIn) {
            showChatMenu(scanner);
        }
        
        scanner.close();
    }
    
    /**
     * Handles user authentication (registration and login) - Part 1
     */
    private static void handleAuthentication(Scanner scanner) {
        System.out.println("\n--- Registration ---");
        
        // Get user details
        System.out.println("Please enter your first name:");
        String firstName = scanner.nextLine();
        System.out.println("Please enter your last name:");
        String lastName = scanner.nextLine();

        currentUser = new Login(firstName, lastName);

        // Registration process with validation loop
        boolean registered = false;
        while (!registered) {
            System.out.println("Please enter a username (must contain '_' and be <= 5 chars):");
            currentUser.setUsername(scanner.nextLine());
            System.out.println("Please enter a password (>=8 chars, cap, num, special char):");
            currentUser.setPassword(scanner.nextLine());
            System.out.println("Please enter your cellphone (+27xxxxxxxxx):");
            currentUser.setCellphone(scanner.nextLine());

            String registrationResult = currentUser.registerUser();
            System.out.println("\n--- Registration Result ---");
            System.out.println(registrationResult);

            if (registrationResult.contains("successfully")) {
                registered = true;
            } else {
                System.out.println("Please try again with valid credentials.\n");
            }
        }

        // Login process with validation loop
        System.out.println("\n--- Login ---");
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("Please enter your username to login:");
            String inputUser = scanner.nextLine();
            System.out.println("Please enter your password to login:");
            String inputPass = scanner.nextLine();

            boolean loginSuccess = currentUser.loginUser(inputUser, inputPass);
            String loginStatus = currentUser.returnLoginStatus(loginSuccess);
            System.out.println(loginStatus);

            if (loginSuccess) {
                loggedIn = true;
                isLoggedIn = true;
                System.out.println("✓ Login successful! Access granted to messaging features.");
            } else {
                System.out.println("Login failed. Please try again.\n");
            }
        }
    }
    
    /**
     * Part 2: Displays the main chat menu and handles message sending
     */
    private static void showChatMenu(Scanner scanner) {
        int choice;
        
        do {
            System.out.println("\n=== QuickChat Menu ===");
            System.out.println("1) Send Messages");
            System.out.println("2) Show recently sent messages");
            System.out.println("3) Quit");
            System.out.print("Please choose an option (1-3): ");
            
            // Handle menu selection
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                choice = 0; // Invalid choice
            }

            switch (choice) {
                case 1:
                    sendMessages(scanner);
                    break;
                case 2:
                    System.out.println("Coming Soon");
                    break;
                case 3:
                    System.out.println("Thank you for using QuickChat. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1, 2, or 3.");
            }
        } while (choice != 3);
    }
    
    /**
     * Part 2: Handles the message sending process
     */
    private static void sendMessages(Scanner scanner) {
        System.out.println("\n--- Send Messages ---");
        
        // Get number of messages to send
        int numMessages = 0;
        while (numMessages <= 0) {
            System.out.print("How many messages do you want to send? ");
            try {
                numMessages = Integer.parseInt(scanner.nextLine());
                if (numMessages <= 0) {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Send specified number of messages using for loop
        for (int i = 0; i < numMessages; i++) {
            System.out.println("\n--- Message " + (i + 1) + " of " + numMessages + " ---");
            sendSingleMessage(scanner);
        }

        // Show total messages sent
        Message tempMsg = new Message(); // To access static counter
        System.out.println("\n--- Summary ---");
        System.out.println("Total messages sent in this session: " + tempMsg.returnTotalMessages());
    }
    
    /**
     * Part 2: Handles sending a single message
     */
    private static void sendSingleMessage(Scanner scanner) {
        Message message = new Message();
        
        // Get and validate recipient
        boolean validRecipient = false;
        while (!validRecipient) {
            System.out.print("Enter recipient cellphone (+27xxxxxxxxx): ");
            String recipient = scanner.nextLine();
            message.setRecipient(recipient);
            
            if (message.checkRecipientCell() == 1) {
                validRecipient = true;
            } else {
                System.out.println("Cell phone number is incorrectly formatted or does not contain international code. Please correct the number and try again.");
            }
        }

        // Get and validate message text
        boolean validMessage = false;
        while (!validMessage) {
            System.out.print("Enter your message (max 250 characters): ");
            String msgText = scanner.nextLine();
            message.setMessageText(msgText);
            
            if (message.checkMessageLength()) {
                validMessage = true;
            } else {
                int overage = message.getMessageOverage();
                System.out.println("Message exceeds 250 characters by " + overage + ", please reduce size.");
            }
        }

        // Generate message hash
        message.createMessageHash();
        
        // Display message details using JOptionPane (as required)
        String messageDetails = "=== Message Details ===\n" + message.printMessages();
        JOptionPane.showMessageDialog(null, messageDetails, "Message Ready", JOptionPane.INFORMATION_MESSAGE);
        
        // Get send option from user
        int sendChoice = 0;
        while (sendChoice < 1 || sendChoice > 3) {
            System.out.println("\nChoose an option:");
            System.out.println("1) Send Message");
            System.out.println("2) Disregard Message");
            System.out.println("3) Store Message to send later");
            System.out.print("Enter your choice (1-3): ");
            
            try {
                sendChoice = Integer.parseInt(scanner.nextLine());
                if (sendChoice < 1 || sendChoice > 3) {
                    System.out.println("Please enter a number between 1 and 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (1-3).");
            }
        }

        // Process send choice
        String result = message.sentMessage(sendChoice);
        System.out.println(result);
        
        // Additional feedback for sent messages
        if (sendChoice == 1) {
            System.out.println("✓ Message sent to " + message.getRecipient());
        }
    }
}
