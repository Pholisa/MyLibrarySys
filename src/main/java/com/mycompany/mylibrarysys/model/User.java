/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mylibrarysys.model;

/**
 *
 * @author Reverside
 */
public class User {

    private int userId;
    private String firstname;
    private String lastname;
    private String emailAddress;
    private String contactNumber;
    private String password;
    private String username;

    
    public User(int userId, String firstname, String lastname, String emailAddress, String contactNumber, String password, String username){
    
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.contactNumber = contactNumber;
        this.password = password;
        this.username = username;

    }
    
        public User(String firstname, String lastname, String emailAddress, String contactNumber, String password, String username){
    
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
        this.contactNumber = contactNumber;
        this.password = password;
        this.username = username;

    }
        
    /**
     * @return the firstname
     */
    public String getFirstname() { return firstname;}

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {this.firstname = firstname;}

    /**
     * @return the lastname
     */
    public String getLastname() {return lastname;}

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {this.lastname = lastname;}

    /**
     * @return the userId
     */
    public int getUserId() {return userId;}

    /**
     * @param userId the userId to set
     */
    public void setUserId(int userId) {this.userId = userId;}
  
     
    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {return emailAddress;}

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {this.emailAddress = emailAddress;}

    /**
     * @return the contactNumber
     */
    public String getContactNumber() {return contactNumber;}

    /**
     * @param contactNumber the contactNumber to set
     */
    public void setContactNumber(String contactNumber) {this.contactNumber = contactNumber;}

    /**
     * @return the password
     */
    public String getPassword() {return password;}

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {this.password = password;}
    
       /**
     * @return the username
     */
    public String getUsername() {return username;}
    
    /**
     * @param username the username to set
     */
    public void setUsername(String username) { this.username = username;}

    
}