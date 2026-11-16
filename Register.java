/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.register;
import java.util.Scanner;
import java.util.ArrayList;

/**
 *
 * @author Student Mduduzi Mashia
 */
public class Register {


    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    //variables that must be declared
    String Username; 
    String Password; 
    String firstName; 
    String lastName;
    String cellphone;
    
    //Details that must be entered by user
    System.out.print("Enter your firstname:");
   String firstname = input.nextLine();
   System.out.print("Enter your lastname:");
   String lastname = input.nextLine();
    System.out.print("Enter your Username:");
    Username = input.nextLine();
     System.out.print("Enter your password:");
   String password = input.nextLine();
   System.out.print("Enter your Cellphone <+27>:");
    cellphone = input.nextLine();
   
   
   
   
   
   //validation method
  Login login = new Login();
    //validation method is used     
   if (login.checkUserName(Username)){
       System.out.println("password successfully captured");
   }
   else {
     System.out.println("password is not correctly formatted,please ensure that the password contains at least 8 characters,a capital letter,a number and a special character.");  
   }
  if (login.checkCellPhoneNumber(cellphone)){
  System.out.println("cell phone number successfully added");
  }
  else {
   System.out.println ("cell phone number incorrectly formtted or does not contain international code.");
  }
  

if (login.checkUserName(Username) && login.checkCellPhoneNumber(cellphone) && login.checkCellPhoneNumber(cellphone)) { 

    System.out.println("Registration Successfully"); 
     System.out.println("");
     System.out.println("__Login__");

     System.out.println("Enter Username");
       String enteredUsername = input.nextLine();   
     System.out.print("Enter password: ");
     String enteredpassword = input.nextLine();
     
     boolean loginstatus = login.loginUser(Username, password, enteredUsername, enteredpassword);
if (loginstatus) {
    System.out.println("Welcome TO QuickChat "); 
     System.out.println("option 1') send Messages");
     System.out.println("option 2') Recently send messages - coming soon");
     System.out.println("option 3') quits");

     int controllerv = input.nextInt();
     input.nextLine();
   switch (controllerv) {
    case 1:
        System.out.println("How many Messages would you like to send?");
        int howMany = input.nextInt();
        input.nextLine();

        for (int i = 1; i <= howMany; i++) {
            System.out.print("Enter recipient (+27...): ");
            String rec = input.nextLine();
            System.out.print("Enter message: ");
            String msgText = input.nextLine();

    if (msgText.length() > 250) {
        System.out.println("Please enter a message of less than 250 characters.");
         i--;
         continue;
  }

    Message m = new Message(rec, msgText, i);
        System.out.println(m.checkRecipientCell());
        System.out.println(m.checkMessageLength());
        System.out.println("Message Hash: " + m.hash);
        System.out.println("1=Send 2=Disregard 3=Store");
       
    int choice = input.nextInt();
        input.nextLine();
        System.out.println(m.sendMessage(choice));
        System.out.println("MessageID: " + m.messageID);
        System.out.println("MessageHash: " + m.hash);
        System.out.println("Recipient: " + m.recipient);
        System.out.println("Message: " + m.message);
        System.out.println("");
 }
        System.out.println("Total messages sent: " + howMany);
        break;
  
    case 2:
      System.out.println("Coming Soon.");
        break;

    case 3:
      System.out.println("Goodbye");
      System.exit(0);
        break;

    default:
     System.out.println("Invalid option");
     break;
 }
  } else {
     System.out.println("Login failed");
    }
     } else {
       System.out.println("Registration UnSuccessfull");
      }
    }

    
 
     // PART 3
    public static ArrayList<String> sentMessages = new ArrayList<>();
    public static ArrayList<String> disregardedMessages = new ArrayList<>();
    public static ArrayList<String> storedMessages = new ArrayList<>();
    public static ArrayList<String> messageHashes = new ArrayList<>();
    public static ArrayList<String> messageIDs = new ArrayList<>();
    public static ArrayList<String> storedSenders = new ArrayList<>();
    public static ArrayList<String> storedRecipients = new ArrayList<>();
    public static ArrayList<Message> allMessages = new ArrayList<>();

    public static void populateTestData() {
        sentMessages.clear();
        disregardedMessages.clear();
        storedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();
        storedSenders.clear();
        storedRecipients.clear();
        allMessages.clear();

        Message m1 = new Message("+27834557896", "Did you get the cake?", 1);
        addToArrays(m1, 1);
        Message m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", 2);
        addToArrays(m2, 3);
        Message m3 = new Message("+27834484567", "Yohooo, I am at your gate.", 3);
        addToArrays(m3, 2);
        Message m4 = new Message("0838884567", "It is dinner time !", 4);
        m4.messageID = "0838884567";
        addToArrays(m4, 1);
        Message m5 = new Message("+27838884567", "Ok, I am leaving without you.", 5);
        addToArrays(m5, 3);
    }

    public static void addToArrays(Message m, int choice) {
        allMessages.add(m);
        messageHashes.add(m.hash);
        messageIDs.add(m.messageID);
        if (choice == 1) {
            sentMessages.add(m.message);
        }
        if (choice == 2) {
            disregardedMessages.add(m.message);
        }
        if (choice == 3) {
            storedMessages.add(m.message);
            storedSenders.add(m.sender);
            storedRecipients.add(m.recipient);
        }
    }

    public static String displayLongestMessage() {
        String longest = "";
        for (int i = 0; i < allMessages.size(); i++) {
            if (allMessages.get(i).message.length() > longest.length()) {
                longest = allMessages.get(i).message;
            }
        }
        return longest;
    }

    public static String searchByMessageID(String id) {
        for (int i = 0; i < allMessages.size(); i++) {
            if (allMessages.get(i).messageID.equals(id)) {
                return "Message: " + allMessages.get(i).message;
            }
            if (allMessages.get(i).recipient.equals(id)) {
                return "Message: " + allMessages.get(i).message;
            }
        }
        return "Not found";
    }

    public static String searchByRecipient(String rec) {
        String result = "";
        for (int i = 0; i < allMessages.size(); i++) {
            if (allMessages.get(i).recipient.equals(rec)) {
                result = result + allMessages.get(i).message + "\n";
            }
        }
        return result;
    }

    public static String deleteByHash(String hash) {
        for (int i = 0; i < allMessages.size(); i++) {
            if (allMessages.get(i).hash.equals(hash)) {
                String text = allMessages.get(i).message;
                allMessages.remove(i);
                messageHashes.remove(i);
                messageIDs.remove(i);
                storedMessages.remove(text);
                sentMessages.remove(text);
                return "Message: \"" + text + "\" successfully deleted.";
            }
        }
        return "Not found";
    }

    public static String displayReport() {
        String report = "";
        for (int i = 0; i < allMessages.size(); i++) {
            report = report + "Message Hash: " + allMessages.get(i).hash + "\n";
            report = report + "Recipient: " + allMessages.get(i).recipient + "\n";
            report = report + "Message: " + allMessages.get(i).message + "\n";
            report = report + "----\n";
        }
        return report;
    }
}
 
 
 
 
 
 
  
  
  
  
  
  

  
    

