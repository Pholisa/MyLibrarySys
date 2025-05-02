/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mylibrarysys.model;

import com.mycompany.mylibrarysys.data.AppQuery;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Reverside
 */
public class Utility {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%&*-_+=?";
    private static final String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARS;

    private static final Random RANDOM = new SecureRandom();

    private Book book;

    private User user;

    //---------------------------------------------------------ADDING BOOK - FINAL------------------------------------------------------------------------------------------------------------------------//
    //FINISH
    public static void addBook(String title, String author, String isbn, String genre, String status, int copies, Window owner) {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add confirmation");
        dialog.setHeaderText("Are you sure you want to add a book?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(owner);

        Label label = new Label("Book Title: " + title + " \n"
                + "Book Author: " + author + " \n"
                + "ISBN: " + isbn + "\n"
                + "Genre: " + genre + "\n"
                + "Status: " + status +"\n"
                + "Copies: " + copies);
        dialog.getDialogPane().setContent(label);

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            Book book = new Book(title, author, isbn, genre, status, copies);
            AppQuery query = new AppQuery();
            query.addBook(book);
        }
    }

    //UPDATING BOOK - FINAL
    public static void updateBook(Book book, Window owner) {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update confirmation");
            dialog.setHeaderText("Are you sure you want to update this book?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(owner);

            Label label = new Label("Book Title: " + book.getName() + " \n"
                    + "Book Author: " + book.getAuthor() + " \n"
                    + "ISBN: " + book.getIsbn() + "\n"
                    + "Genre: " + book.getGenre() + "\n"
                    + "Copies: " + book.getCopies());
            dialog.getDialogPane().setContent(label);

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                AppQuery query = new AppQuery();
                query.updateBook(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //DELETING BOOK - FINAL
    //FINISH
    public static void deleteBook(Book book, Window owner) {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Remove confirmation");
            dialog.setHeaderText("Are you sure you want to remove this book?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(owner);

            Label label = new Label("Book Title: " + book.getName() + " \n"
                    + "Book Author: " + book.getAuthor() + " \n"
                    + "ISBN: " + book.getIsbn() + "\n"
                    + "Genre: " + book.getGenre() + "\n"
                    + "Status: " + book.getStatus());
            dialog.getDialogPane().setContent(label);

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                AppQuery query = new AppQuery();
                query.deleteBook(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //------MEMEBR MANAGEMENT-------------------------------------------------------------------------------------------------------------------------//
    //AUTOMATED PASSWORD
    //FINISH
    public static String generatePassword(int length) {
        if (length < 6) { // Ensuring password is strong
            throw new IllegalArgumentException("Password length must be at least 6 characters.");
        }

        StringBuilder password = new StringBuilder(length);

        // Ensure at least one character from each category
        password.append(UPPERCASE.charAt(RANDOM.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(RANDOM.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(RANDOM.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARS.charAt(RANDOM.nextInt(SPECIAL_CHARS.length())));

        // Fill remaining length with random characters from all categories
        for (int i = 4; i < length; i++) {
            password.append(ALL_CHARS.charAt(RANDOM.nextInt(ALL_CHARS.length())));
        }

        // Shuffle to remove predictable patterns
        return shuffleString(password.toString());
    }

    //FINISH
    private static String shuffleString(String input) {
        char[] array = input.toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int j = RANDOM.nextInt(i + 1);
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        return new String(array);
    }

    //MEMBER REGISTRATION BY LIBRARIAN - FINAL 
    //FINISH
    public void registerUser(TextField regFirstname, TextField regLastname, TextField regEmailAddress, TextField regContactNumber, String regPassword, TextField username) {
        if (regFirstname.getText().isEmpty() || regLastname.getText().isEmpty() || regContactNumber.getText().isEmpty()
                || regPassword == null) {

            showAlert(Alert.AlertType.ERROR, "Error", "All fields are required!");
            return;
        }
        User user = new User(regFirstname.getText(), regLastname.getText(), regEmailAddress.getText(), regContactNumber.getText(), regPassword, username.getText());

        AppQuery query = new AppQuery();
        if (query.addUser(user)) {
            showAlert(Alert.AlertType.INFORMATION, "Success", "User Registered Successfully!");

        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "User Registration Failed!");

        }

    }

//MEMBER UPDATE BY LIBRARIAN - FINAL 
    //FINISH
    public static void updateUser(User user, Window owner) {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update confirmation");
            dialog.setHeaderText("Are you sure you want to update this user?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(owner);

            Label label = new Label("Name: " + user.getFirstname() + " \n"
                    + "Surname: " + user.getLastname() + " \n"
                    + "Email Address: " + user.getEmailAddress() + "\n"
                    + "Contact Number: " + user.getContactNumber());
            dialog.getDialogPane().setContent(label);

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                AppQuery query = new AppQuery();
                query.updateUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //MEMBER DELETE BY LIBRARIAN - FINAL 
    //FINISH
    public static void deleteUser(User user, Window owner) {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Remove confirmation");
            dialog.setHeaderText("Are you sure you want to remove this user?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(owner);

            Label label = new Label("Name: " + user.getFirstname() + " \n"
                    + "Surname: " + user.getLastname() + " \n"
                    + "Email Address: " + user.getEmailAddress() + "\n"
                    + "Contact Number: " + user.getContactNumber());
            dialog.getDialogPane().setContent(label);

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                AppQuery query = new AppQuery();
                query.deleteUser(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void changePassword(User user, Window owner) {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Change Paasword Confirmation");
            dialog.setHeaderText("Are you sure you want to change password the password for the user with this information?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(owner);

            Label label = new Label("Username: " + user.getUsername() + " \n"
                    + "Surname: " + user.getLastname());
            dialog.getDialogPane().setContent(label);

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                AppQuery query = new AppQuery();
                query.changePassword(user);
                showAlert("Success", "Password changed successfully!", Alert.AlertType.INFORMATION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------FILTERING: SEARCH - FINAL------------------------------------------------------------------------------------------//
    //FINISH
    public static ObservableList<Book> filterBooks(String searchName) {
        ObservableList<Book> filteredBooks = FXCollections.observableArrayList();
        AppQuery query = new AppQuery();
        ObservableList<Book> books = query.getBookList();

        for (Book book : books) {
            if (book.getName().toLowerCase().contains(searchName.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(searchName.toLowerCase())
                    || book.getIsbn().toLowerCase().contains(searchName.toLowerCase())
                    || book.getGenre().toLowerCase().contains(searchName.toLowerCase())
                    || book.getStatus().toLowerCase().contains(searchName.toLowerCase())) {
                filteredBooks.add(book);
            }
        }
        return filteredBooks;
    }

    //FINISH
    public static ObservableList<User> filterUsers(String searchName) {
        ObservableList<User> filteredUsers = FXCollections.observableArrayList();
        AppQuery query = new AppQuery();
        ObservableList<User> users = query.getUserList();

        for (User user : users) {
            if (user.getFirstname().toLowerCase().contains(searchName.toLowerCase())
                    || user.getLastname().toLowerCase().contains(searchName.toLowerCase())
                    || user.getEmailAddress().toLowerCase().contains(searchName.toLowerCase())
                    || user.getContactNumber().toLowerCase().contains(searchName.toLowerCase())) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

    //-------------------------------------------------------------------------POPULATE TABLES - FINAL-------------------------------------------------------------//
    public static void populateBookTable(
            TableView<Book> tableView,
            TableColumn<Book, Integer> idColumn,
            TableColumn<Book, String> titleColumn,
            TableColumn<Book, String> authorColumn,
            TableColumn<Book, String> isbnColumn,
            TableColumn<Book, String> genreColumn,
            TableColumn<Book, String> statusColumn) {

        AppQuery query = new AppQuery();
        ObservableList<Book> list = query.getBookList();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableView.setItems(list);

        // Disable books that are unavailable
        tableView.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                if (book != null && "Unavailable".equals(book.getStatus())) {
                    setDisable(true);
                    setStyle("-fx-background-color: lightgray;");
                } else {
                    setDisable(false);
                    setStyle(""); // Reset style for available books
                }
            }
        });
    }
    
    //FINISH
    public static void populateCrucBookTable(
            TableView<Book> tableView,
            TableColumn<Book, Integer> idColumn,
            TableColumn<Book, String> titleColumn,
            TableColumn<Book, String> authorColumn,
            TableColumn<Book, String> isbnColumn,
            TableColumn<Book, String> genreColumn,
            TableColumn<Book, String> statusColumn,
            TableColumn<Book, String> copiesColumn) {

        AppQuery query = new AppQuery();
        ObservableList<Book> list = query.getCruBookList();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        copiesColumn.setCellValueFactory(new PropertyValueFactory<>("copies"));

        tableView.setItems(list);

        // Disable books that are unavailable
        tableView.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                if (book != null && "Unavailable".equals(book.getStatus())) {
                    
                    setStyle("-fx-background-color: lightgray;");
                } else {
                    setDisable(false);
                    setStyle(""); // Reset style for available books
                }
            }
        });
    }


    public static void populateBorrowBookTable(
            TableView<Book> tableView,
            TableColumn<Book, Integer> idColumn,
            TableColumn<Book, String> titleColumn,
            TableColumn<Book, String> authorColumn,
            TableColumn<Book, String> isbnColumn,
            TableColumn<Book, String> genreColumn,
            TableColumn<Book, String> statusColumn) {

        AppQuery query = new AppQuery();

        //AppQuery query = new AppQuery();
        ObservableList<Book> list = query.getBookList();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        tableView.setItems(list);

        // Disable books that are unavailable
        tableView.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                if (book != null && "Unavailable".equals(book.getStatus())) {
                    setDisable(true);
                    setStyle("-fx-background-color: lightgray;"); // Optional: visually indicate it's unavailable
                } else {
                    setDisable(false);
                    setStyle(""); // Reset style for available books
                }
            }
        });
    }

    //FINAL
    public static void populateUserTable(
            TableView<User> tableView,
            TableColumn<User, Integer> idColumn,
            TableColumn<User, String> nameColumn,
            TableColumn<User, String> surnameColumn,
            TableColumn<User, String> emailColumn,
            TableColumn<User, String> numberColumn,
            TableColumn<User, String> usernameColumn) {

        AppQuery query = new AppQuery();
        ObservableList<User> list = query.getUserList();

        idColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        tableView.setItems(list);
    }

//FINAL
    public static void populateBorrowedBooksTable(
            TableView<BorrowedBooks> tableView,
            TableColumn<BorrowedBooks, Integer> idColumn,
            TableColumn<BorrowedBooks, String> bookNameColumn,
            TableColumn<BorrowedBooks, Date> issueDateColumn,
            TableColumn<BorrowedBooks, Date> returnDateColumn,
            TableColumn<BorrowedBooks, Date> dateReturnedColumn,
            TableColumn<BorrowedBooks, Integer> daysOverdueColumn,
            TableColumn<BorrowedBooks, Integer> feeColumn,
            int userId) {

        // Initialize your database query class
        AppQuery query = new AppQuery();

        //ObservableList<User> list = query.getUserList();
        // Retrieve borrowed books list from the database for a particular user
        ObservableList<BorrowedBooks> borrowedBookList = query.getBorrowedBooksByUser(userId); // Example userId: 1

        // Set up the table columns with the appropriate data fields from BorrowedBook
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        bookNameColumn.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        issueDateColumn.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("returnDate"));
        dateReturnedColumn.setCellValueFactory(new PropertyValueFactory<>("dateReturned"));
        daysOverdueColumn.setCellValueFactory(new PropertyValueFactory<>("daysOverdue"));
        feeColumn.setCellValueFactory(new PropertyValueFactory<>("fee"));

        // Set the items in the tableView to the borrowed books list
        tableView.setItems(borrowedBookList);
        
        
        

    }

    //
    //------------BORROWING BOOK - FINAL ----------------------------------------------------------------------------------------------------------------// 
    //FINAL
    public static void handleBorrowButton(Book selectedBook, Window owner) {
        if (selectedBook != null) {

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update Confirmation");
            dialog.setHeaderText("Are you sure you want to borrow this book?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(owner);

            Label label = new Label("Book: " + selectedBook.getName() + "\n"
                    + "Author: " + selectedBook.getAuthor() + "\n"
                    + "ISBN: " + selectedBook.getIsbn() + "\n"
                    + "Genre: " + selectedBook.getGenre());
            dialog.getDialogPane().setContent(label);

            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

            dialog.showAndWait().ifPresent(response -> {
                if (response == okButton) {
                    AppQuery query = new AppQuery();
                    query.borrowBook(selectedBook.getId());
                    refreshBookList();

                    showAlert("Success", "The book has been borrowed successfully!", Alert.AlertType.INFORMATION);
                } else {

                    showAlert("Cancelled", "The borrowing action has been cancelled.", Alert.AlertType.INFORMATION);
                }
            });
        } else {

            showAlert("Error", "Please select a book to borrow.", Alert.AlertType.ERROR);
        }
    }
//FINAL

    public static void processReturnBook(TableView<BorrowedBooks> tableView, int bookId, int borrowedBookId, int userId) {

        System.out.println("Returning books UTILITY");
        // Show confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to return this book?", ButtonType.YES, ButtonType.NO);
        alert.setHeaderText("Confirm Return");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            // Initialize your database query class
            AppQuery query = new AppQuery();
            query.processReturnBook(bookId, borrowedBookId);  // Call the query to return the book

            // Refresh TableView
            ObservableList<BorrowedBooks> list = query.getBorrowedBooksByUser(userId);
            tableView.setItems(list);
        }
    }

    //----------------------REFRESHING BOOK LIST - FINAL -------------------------------------------------------------------------------------------------------------------------//   
    //FINAL
    private static void refreshBookList() {
        System.out.println("Refreshing the book list...");
    }

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //FINAL
    public static void insertBookBorrowed(int userId, int bookId, String bookName) {

        AppQuery query = new AppQuery();
        query.insertBookBorrowed(userId, bookId, bookName);
    }

    //----------------------------------------------------------------SHOW ALERTS - FINAL-----------------------------------------------------------------------------------------------------//
    //FINAL
    public void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    //FINAL
    public static void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
