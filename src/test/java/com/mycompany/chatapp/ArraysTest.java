/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.chatapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lab_services_student
 */
public class ArraysTest {
 
     private Arrays messageArrays;
     
      @BeforeEach
    public void setUp() {
        // Initialize the Arrays class with a maximum of 5 messages for testing
        messageArrays = new Arrays(5);
    }
    
    @Test
    public void testAddSentMessage() {
        messageArrays.addSentMessage("Test message 1", "hash1", "id1");
        assertEquals(1, messageArrays.getSentMessagesCount());
        assertEquals("Test message 1", messageArrays.getSentMessage(0));
        assertEquals("hash1", messageArrays.messageHashes[0]); // Accessing directly for testing
        assertEquals("id1", messageArrays.messageIDs[0]); // Accessing directly for testing
    }
    
     @Test
    public void testAddDisregardedMessage() {
        messageArrays.addDisregardedMessage("Disregarded message 1");
        assertEquals(1, messageArrays.getDisregardedMessagesCount());
        assertEquals("Disregarded message 1", messageArrays.getDisregardedMessage(0));
    }
    
     @Test
    public void testGetSentMessagesCount() {
        assertEquals(0, messageArrays.getSentMessagesCount());
        messageArrays.addSentMessage("Test message 1", "hash1", "id1");
        assertEquals(1, messageArrays.getSentMessagesCount());
    }
    
     @Test
    public void testGetDisregardedMessagesCount() {
        assertEquals(0, messageArrays.getDisregardedMessagesCount());
        messageArrays.addDisregardedMessage("Disregarded message 1");
        assertEquals(1, messageArrays.getDisregardedMessagesCount());
    }
    
    @Test
    public void testGetSentMessage() {
        messageArrays.addSentMessage("Test message 1", "hash1", "id1");
        assertEquals("Test message 1", messageArrays.getSentMessage(0));
        assertNull(messageArrays.getSentMessage(1)); // Out of bounds
    }
    
    @Test
    public void testGetDisregardedMessage() {
        messageArrays.addDisregardedMessage("Disregarded message 1");
        assertEquals("Disregarded message 1", messageArrays.getDisregardedMessage(0));
        assertNull(messageArrays.getDisregardedMessage(1)); // Out of bounds
    }
    
    @Test
    public void testGetAllSentMessages() {
        messageArrays.addSentMessage("Test message 1", "hash1", "id1");
        messageArrays.addSentMessage("Test message 2", "hash2", "id2");
        String[] sentMessages = messageArrays.getAllSentMessages();
        assertEquals(2, sentMessages.length);
        assertEquals("Test message 1", sentMessages[0]);
        assertEquals("Test message 2", sentMessages[1]);
    }
    
     @Test
    public void testGetAllDisregardedMessages() {
        messageArrays.addDisregardedMessage("Disregarded message 1");
        messageArrays.addDisregardedMessage("Disregarded message 2");
        String[] disregardedMessages = messageArrays.getAllDisregardedMessages();
        assertEquals(2, disregardedMessages.length);
        assertEquals("Disregarded message 1", disregardedMessages[0]);
        assertEquals("Disregarded message 2", disregardedMessages[1]);
    }
}