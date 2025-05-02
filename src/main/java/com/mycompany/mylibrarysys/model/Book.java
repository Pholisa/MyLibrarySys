/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mylibrarysys.model;

/**
 *
 * @author Reverside
 */
public class Book {

    
    private Integer id;
    private String name;
    private String author;
    private String isbn;
    private String genre;
    private String status;
    private Integer copies;
    

    public Book(Integer id, String name,String author, String isbn, String genre, String status, Integer copies ) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.status = status;
        this.copies = copies;
    }

    public Book(String name,String author, String isbn, String genre, String status, Integer copies) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.isbn = isbn;
        this.genre = genre;
        this.status = status;
        this.copies = copies;
    }

    /**
     * @return the id
     */
    public Integer getId() {return id; }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {this.id = id; }

    /**
     * @return the name
     */
    public String getName() {return name; }

    /**
     * @param name the name to set
     */
    public void setName(String author) {this.name = name; }
    
    /**
     * @return the author
     */
    public String getAuthor() {return author;}

    /**
     * @param author the bookAuthor to set
     */
    public void setBookAuthor(String author) {this.author = author;}

    /**
     * @return the isbn
     */
    public String getIsbn() {return isbn;}

    /**
     * @param isbn the isbn to set
     */
    public void setIsbn(String isbn) {this.isbn = isbn;}

    /**
     * @return the genre
     */
    public String getGenre() {return genre;}

    /**
     * @param genre the bookAuthor to set
     */
    public void setGenre(String genre) {this.genre = genre;}

    /**
     * @return the status
     */
    public String getStatus() {return status;}

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {this.status = status;}
    
    /**
     * @return the copies
     */
    public Integer getCopies() {return copies;}

    /**
     * @param copies the copies to set
     */
    public void setCopies(Integer copies) {this.copies = copies;}

}



