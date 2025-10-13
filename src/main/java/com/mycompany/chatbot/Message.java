/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatbot;
import java.util.Random;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author RC_Student_Lab
 * Message class for handling chat messages
 * Implements message validation, hashing, and storage functionality
 */
public class Message {
     private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    private static int totalMessagesSent = 0;
    private static int messageCounter = 0;

    // Constructor
    public Message() {
        this.messageNumber = ++messageCounter;
        generateMessageID();
    }

    /**
     * Generates a random 10-digit message ID
     */
    private void generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long)(rand.nextDouble() * 9000000000L);
        this.messageID = String.valueOf(id);
    }

    /**
     * Checks if message ID is valid (exactly 10 characters)
     * @return boolean - true if valid
     */
    public boolean checkMessageID() {
        return messageID != null && messageID.length() == 10;
    }

    /**
     * Validates recipient cell number format
     * @return int - 1 if valid, 0 if invalid
     */
    public int checkRecipientCell() {
        if (recipient == null) return 0;
        
        // Reuse the same regex from Login class for consistency
        String regex = "^\\+27[0-9]{9}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(recipient);
        return matcher.matches() ? 1 : 0;
    }

    /**
     * Creates message hash in format: first2ID:messageNum:FIRSTWORDLASTWORD
     * @return String - the generated message hash
     */
    public String createMessageHash() {
        if (messageID == null || messageText == null) return "";
        
        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 0 ? words[words.length - 1] : "";
        
        this.messageHash = firstTwo + ":" + messageNumber + ":" + 
                          firstWord.toUpperCase() + lastWord.toUpperCase();
        return this.messageHash;
    }

    /**
     * Handles message sending options
     * @return String - status message based on user choice
     */
    public String sentMessage(int choice) {
        switch (choice) {
            case 1: // Send
                totalMessagesSent++;
                return "Message successfully sent.";
            case 2: // Disregard
                return "Press 0 to delete message.";
            case 3: // Store
                return "Message successfully stored.";
            default:
                return "Invalid choice.";
        }
    }

    /**
     * Returns formatted message details
     * @return String - formatted message information
     */
    public String printMessages() {
        return "MessageID: " + messageID + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: " + recipient + "\n" +
               "Message: " + messageText;
    }

    /**
     * Returns total number of messages sent
     * @return int - total messages sent
     */
    public int returnTotalMessages() {
        return totalMessagesSent;
    }

    /**
     * Validates message length (max 250 characters)
     * @return boolean - true if valid length
     */
    public boolean checkMessageLength() {
        return messageText != null && messageText.length() <= 250;
    }

    /**
     * Gets the character count overage if message is too long
     * @return int - number of characters over limit
     */
    public int getMessageOverage() {
        if (messageText == null) return 0;
        return Math.max(0, messageText.length() - 250);
    }

    // Getters and Setters
    public String getMessageID() { return messageID; }
    public void setMessageID(String messageID) { this.messageID = messageID; }
    
    public int getMessageNumber() { return messageNumber; }
    public void setMessageNumber(int messageNumber) { this.messageNumber = messageNumber; }
    
    public String getRecipient() { return recipient; }
    public void setRecipient(String recipient) { this.recipient = recipient; }
    
    public String getMessageText() { return messageText; }
    public void setMessageText(String messageText) { this.messageText = messageText; }
    
    public String getMessageHash() { return messageHash; }
    public void setMessageHash(String messageHash) { this.messageHash = messageHash; }
}
