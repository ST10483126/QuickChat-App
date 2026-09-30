/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.Chatapp;

import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author lab_services_student
 */
public class Message {
    private String messageId;
    private String messageHash;
    private String recipient;
    private String messageContent;
    private static int totalMessagesSent = 0;

    public Message(String recipient, String messageContent) {
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageId = generateMessageId();
        this.messageHash = createMessageHash();
    }

    private String generateMessageId() {
        Random random = new Random();
        return String.format("%010d", random.nextInt(1000000000));  // 10-digit number
    }

    public boolean checkMessageID() {
        return messageId != null && messageId.length() == 10;
    }

     public String checkRecipientCell() {
        if (recipient == null) {
            return "Error: Recipient cell number cannot be null.";
        } else if (recipient.length() > 10) {
            return "Error: Recipient cell number cannot exceed 10 characters.";
        } else {
            return "Valid recipient cell number.";
        }
    }

    public String createMessageHash() {
        int firstSpace = messageContent.indexOf(' ');
        String firstWord = (firstSpace == -1) ? messageContent : messageContent.substring(0, firstSpace);

        int lastSpace = messageContent.lastIndexOf(' ');
        String lastWord = (lastSpace == -1 || lastSpace == firstSpace) ? firstWord : messageContent.substring(lastSpace + 1);

        return (messageId.substring(0, 2) + ":" + messageId.charAt(0) + ":" + firstWord.toUpperCase() + lastWord.toUpperCase());
    }

    public String sentMessage() {
        return "MessageID: " + messageId + "\nMessageHash: " + messageHash + "\nRecipient: " + recipient + "\nMessage: " + messageContent;
    }

    public static int returnTotalMessages() {
        return totalMessagesSent;
    }

    public void storeMessage() {
        totalMessagesSent++;  // Increment on every store attempt

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("MessageID", messageId);
        jsonObject.put("MessageHash", messageHash);
        jsonObject.put("Recipient", recipient);
        jsonObject.put("Message", messageContent);

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(jsonObject.toString() + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

