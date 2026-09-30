/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author lab_services_student
 */
public class Arrays {
    private String[] sentMessages;
    private String[] disregardedMessages;
    private String[] storedMessages;
    String[] messageHashes;
    String[] messageIDs;
    
     private int sentMessagesCount = 0;
    private int disregardedMessagesCount = 0;
    private int storedMessagesCount = 0;
    private int maxMessages;
    
    
     public Arrays(int maxMessages) {
        this.maxMessages = maxMessages;
        this.sentMessages = new String[maxMessages];
        this.disregardedMessages = new String[maxMessages];
        this.storedMessages = new String[maxMessages];
        this.messageHashes = new String[maxMessages];
        this.messageIDs = new String[maxMessages];
        }
     // For adding sent messages
      public void addSentMessage(String message, String hash, String id) {
        if (sentMessagesCount < maxMessages) {
            sentMessages[sentMessagesCount] = message;
            messageHashes[sentMessagesCount] = hash;
            messageIDs[sentMessagesCount] = id;
            sentMessagesCount++;
        }
    }
      //For adding diregarded messages
      // Add a disregarded message
    public void addDisregardedMessage(String message) {
        if (disregardedMessagesCount < maxMessages) {
            disregardedMessages[disregardedMessagesCount] = message;
            disregardedMessagesCount++;
        }
    }
    
    // Get the count of sent messages
    public int getSentMessagesCount() {
        return sentMessagesCount;
    }
    
     public int getDisregardedMessagesCount() {
        return disregardedMessagesCount;
    }
     
      public String getSentMessage(int index) {
        if (index >= 0 && index < sentMessagesCount) {
            return sentMessages[index];
        }
        return null;
    }
      
       public String getDisregardedMessage(int index) {
        if (index >= 0 && index < disregardedMessagesCount) {
            return disregardedMessages[index];
        }
        return null;
    }
       
        public String[] getAllSentMessages() {
        String[] messages = new String[sentMessagesCount];
        System.arraycopy(sentMessages, 0, messages, 0, sentMessagesCount);
        return messages;
    }
        
         public String[] getAllDisregardedMessages() {
        String[] messages = new String[disregardedMessagesCount];
        System.arraycopy(disregardedMessages, 0, messages, 0, disregardedMessagesCount);
        return messages;
    }
}


