/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.register;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Student
 */

public class MessageTest {
    
    public MessageTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        Register.populateTestData();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testSentMessagesArrayCorrectlyPopulated() {
        System.out.println("sentMessages");
        String expResult = "Did you get the cake?";
        String result = Register.sentMessages.get(0);
        assertEquals(expResult, result);
        expResult = "It is dinner time !";
        result = Register.sentMessages.get(1);
        assertEquals(expResult, result);
    }

    @Test
    public void testDisplayLongestMessage() {
        System.out.println("displayLongestMessage");
        String expResult = "Where are you? You are late! I have asked you to be on time.";
        String result = Register.displayLongestMessage();
        assertEquals(expResult, result);
    }

    @Test
    public void testSearchForMessageID() {
        System.out.println("searchByMessageID");
        String expResult = "It is dinner time !";
        String result = Register.searchByMessageID("0838884567");
        assertTrue(result.contains(expResult));
    }

    @Test
    public void testSearchAllMessagesForRecipient() {
        System.out.println("searchByRecipient");
        String result = Register.searchByRecipient("+27838884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(result.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageUsingHash() {
        System.out.println("deleteByHash");
        String hash = Register.allMessages.get(1).hash;
        String expResult = "Message: \"Where are you? You are late! I have asked you to be on time.\" successfully deleted.";
        String result = Register.deleteByHash(hash);
        assertEquals(expResult, result);
    }

    @Test
    public void testDisplayReport() {
        System.out.println("displayReport");
        String result = Register.displayReport();
        assertTrue(result.contains("Message Hash:"));
        assertTrue(result.contains("Recipient:"));
        assertTrue(result.contains("Message:"));
    }
}