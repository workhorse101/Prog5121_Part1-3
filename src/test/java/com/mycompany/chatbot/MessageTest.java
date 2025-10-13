/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany.chatbot;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RC_Student_Lab
 */
public class MessageTest {
       private Message message;
    
    public MessageTest() {
    }
    
    @Before
    public void setUp() {
        message = new Message();
        message.setRecipient("+27831234567");
        message.setMessageText("Hello world test message");
    }

    /**
     * Test of checkMessageID method, of class Message.
     */
    @Test
    public void testCheckMessageID() {
        // Test auto-generated message ID
        assertTrue("Auto-generated message ID should be valid", message.checkMessageID());
        
        // Test manually set valid ID
        message.setMessageID("1234567890");
        assertTrue("10-digit message ID should be valid", message.checkMessageID());
        
        // Test invalid ID
        message.setMessageID("12345");
        assertFalse("5-digit message ID should be invalid", message.checkMessageID());
    }

    /**
     * Test of checkRecipientCell method, of class Message.
     */
    @Test
    public void testCheckRecipientCell() {
        // Test valid recipient
        assertEquals("Valid cell number should return 1", 1, message.checkRecipientCell());
        
        // Test invalid recipient without international code
        message.setRecipient("0831234567");
        assertEquals("Cell number without international code should return 0", 0, message.checkRecipientCell());
        
        // Test invalid recipient with wrong country code
        message.setRecipient("+25831234567");
        assertEquals("Cell number with wrong country code should return 0", 0, message.checkRecipientCell());
    }

    /**
     * Test of createMessageHash method, of class Message.
     */
    @Test
    public void testCreateMessageHash() {
        message.setMessageID("1234567890");
        message.setMessageText("Hello world test message");
        String hash = message.createMessageHash();
        
        // Check hash format: first2ID:messageNum:FIRSTWORDLASTWORD
        assertTrue("Hash should start with first two digits of message ID", hash.startsWith("12:"));
        assertTrue("Hash should contain message number", hash.contains(":1:"));
        assertTrue("Hash should contain first and last words in uppercase", hash.contains("HELLOMESSAGE"));
    }

    /**
     * Test of sentMessage method, of class Message.
     */
    @Test
    public void testSentMessage() {
        // Test send option
        String result1 = message.sentMessage(1);
        assertEquals("Send option should return success message", "Message successfully sent.", result1);
        
        // Test disregard option
        String result2 = message.sentMessage(2);
        assertEquals("Disregard option should return delete prompt", "Press 0 to delete message.", result2);
        
        // Test store option
        String result3 = message.sentMessage(3);
        assertEquals("Store option should return storage message", "Message successfully stored.", result3);
        
        // Test invalid option
        String result4 = message.sentMessage(99);
        assertEquals("Invalid choice should return error message", "Invalid choice.", result4);
    }

    /**
     * Test of printMessages method, of class Message.
     */
    @Test
    public void testPrintMessages() {
        message.setMessageID("1234567890");
        message.setRecipient("+27831234567");
        message.setMessageText("Test message for display");
        message.createMessageHash();
        
        String output = message.printMessages();
        
        assertTrue("Output should contain MessageID", output.contains("MessageID: 1234567890"));
        assertTrue("Output should contain Recipient", output.contains("Recipient: +27831234567"));
        assertTrue("Output should contain Message", output.contains("Message: Test message for display"));
        assertTrue("Output should contain Message Hash", output.contains("Message Hash: "));
    }

    /**
     * Test of returnTotalMessages method, of class Message.
     */
    @Test
    public void testReturnTotalMessages() {
        // Create new messages and send them to test counter
        Message msg1 = new Message();
        Message msg2 = new Message();
        
        msg1.sentMessage(1); // Send - should increment counter
        msg2.sentMessage(1); // Send - should increment counter
        
        int total = msg1.returnTotalMessages();
        assertTrue("Total messages should be at least 2", total >= 2);
    }

    /**
     * Test of checkMessageLength method, of class Message.
     */
    @Test
    public void testCheckMessageLength() {
        // Test valid message length
        message.setMessageText("Short message");
        assertTrue("Message within 250 chars should be valid", message.checkMessageLength());
        
        // Test exactly 250 characters
        String exact250 = "A".repeat(250);
        message.setMessageText(exact250);
        assertTrue("Message with exactly 250 chars should be valid", message.checkMessageLength());
        
        // Test invalid message length
        String longMessage = "A".repeat(251);
        message.setMessageText(longMessage);
        assertFalse("Message over 250 chars should be invalid", message.checkMessageLength());
    }

    /**
     * Test of getMessageOverage method, of class Message.
     */
    @Test
    public void testGetMessageOverage() {
        // Test no overage
        message.setMessageText("Short message");
        assertEquals("Short message should have 0 overage", 0, message.getMessageOverage());
        
        // Test with overage
        String longMessage = "A".repeat(260);
        message.setMessageText(longMessage);
        assertEquals("260-character message should have 10 overage", 10, message.getMessageOverage());
    }

    /**
     * Test of getMessageID method, of class Message.
     */
    @Test
    public void testGetMessageID() {
        message.setMessageID("9876543210");
        assertEquals("getMessageID should return the set value", "9876543210", message.getMessageID());
    }

    /**
     * Test of setMessageID method, of class Message.
     */
    @Test
    public void testSetMessageID() {
        message.setMessageID("1111111111");
        assertEquals("setMessageID should update the message ID", "1111111111", message.getMessageID());
    }

    /**
     * Test of getMessageNumber method, of class Message.
     */
    @Test
    public void testGetMessageNumber() {
        int messageNumber = message.getMessageNumber();
        assertTrue("Message number should be positive", messageNumber > 0);
    }

    /**
     * Test of setMessageNumber method, of class Message.
     */
    @Test
    public void testSetMessageNumber() {
        message.setMessageNumber(5);
        assertEquals("setMessageNumber should update the message number", 5, message.getMessageNumber());
    }

    /**
     * Test of getRecipient method, of class Message.
     */
    @Test
    public void testGetRecipient() {
        assertEquals("getRecipient should return the set value", "+27831234567", message.getRecipient());
    }

    /**
     * Test of setRecipient method, of class Message.
     */
    @Test
    public void testSetRecipient() {
        message.setRecipient("+27839876543");
        assertEquals("setRecipient should update the recipient", "+27839876543", message.getRecipient());
    }

    /**
     * Test of getMessageText method, of class Message.
     */
    @Test
    public void testGetMessageText() {
        assertEquals("getMessageText should return the set value", "Hello world test message", message.getMessageText());
    }

    /**
     * Test of setMessageText method, of class Message.
     */
    @Test
    public void testSetMessageText() {
        message.setMessageText("New test message");
        assertEquals("setMessageText should update the message text", "New test message", message.getMessageText());
    }

    /**
     * Test of getMessageHash method, of class Message.
     */
    @Test
    public void testGetMessageHash() {
        message.setMessageHash("TEST:HASH:123");
        assertEquals("getMessageHash should return the set value", "TEST:HASH:123", message.getMessageHash());
    }

    /**
     * Test of setMessageHash method, of class Message.
     */
    @Test
    public void testSetMessageHash() {
        message.setMessageHash("12:1:HELLOMESSAGE");
        assertEquals("setMessageHash should update the message hash", "12:1:HELLOMESSAGE", message.getMessageHash());
    }
    
    /**
     * Test message number auto-increment functionality
     */
    @Test
    public void testMessageNumberAutoIncrement() {
        Message msg1 = new Message();
        Message msg2 = new Message();
        Message msg3 = new Message();
        
        assertTrue("Message numbers should auto-increment", 
                   msg2.getMessageNumber() > msg1.getMessageNumber());
        assertTrue("Message numbers should auto-increment", 
                   msg3.getMessageNumber() > msg2.getMessageNumber());
    }
    
    /**
     * Test that only sent messages increment the total counter
     */
    @Test
    public void testTotalMessagesCounter() {
        // Reset by using current count as baseline
        int initialCount = message.returnTotalMessages();
        
        Message msg1 = new Message();
        Message msg2 = new Message();
        Message msg3 = new Message();
        
        msg1.sentMessage(1); // Send - should count
        msg2.sentMessage(2); // Disregard - should NOT count
        msg3.sentMessage(3); // Store - should NOT count
        
        int newCount = message.returnTotalMessages();
        assertTrue("Only sent messages should increment total counter", newCount >= initialCount + 1);
    }
}
