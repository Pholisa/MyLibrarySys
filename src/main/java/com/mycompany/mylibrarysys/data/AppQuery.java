/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mylibrarysys.data;

import com.mycompany.mylibrarysys.model.Book;
import com.mycompany.mylibrarysys.model.BorrowedBooks;
import com.mycompany.mylibrarysys.model.User;
import com.mycompany.mylibrarysys.model.Utility;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

/**
 *
 * @author Reverside
 */
public class AppQuery {

    private DBConnection conn = new DBConnection();

    //------------------------------USER ACCESS------------------------------------------------------------------------------------------------------------------------------------------------------------//
    //USER REGISTRATION: FINAL
    //FINISH
    public boolean addUser(User user) {
        try {
            conn.getDBConnect();
            Connection connection = conn.getConnect();

            if (connection == null || connection.isClosed()) {
                System.out.println("Error: Database connection failed!");
                return false;
            }

            String sql = "INSERT INTO user_reg(firstname, lastname, email_address, contact_number, user_password, username) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            //Hash Password using BCrypt
            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getEmailAddress());
            ps.setString(4, user.getContactNumber());
            ps.setString(5, hashedPassword);
            ps.setString(6, user.getUsername());

            int rowsInserted = ps.executeUpdate();
            ps.close();
            conn.closeConnection();

            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println("Registration Failed: " + e.getMessage());
            return false;
        }
    }

    //LOGIN USER: FINAL
    //FINISH
    public boolean loginUser(String username, String password) {
        try {
            conn.getDBConnect();
            Connection connection = conn.getConnect();

            String sql = "SELECT user_password FROM user_reg WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("user_password");
                ps.close();
                conn.closeConnection();

                // Compare hashed passwords
                return BCrypt.checkpw(password, storedPassword);
            }
            return false;
        } catch (SQLException e) {
            System.out.println("Login Failed: " + e.getMessage());
            return false;
        }
    }

    
    //FINISH
    public Integer getUserId(String username, String password) {
        try {
            conn.getDBConnect();
            Connection connection = conn.getConnect();

            String sql = "SELECT userid, user_password FROM user_reg WHERE username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("user_password");
                // Correct column name
                int userId = rs.getInt("userid");

                ps.close();
                conn.closeConnection();

                // Compare hashed passwords
                if (BCrypt.checkpw(password, storedPassword)) {
                    // Return correct user ID
                    return userId;
                }
            }
        } catch (SQLException e) {
            System.out.println("Login Failed: " + e.getMessage());
        }
        // Return null if login fails
        return null;
    }


    //----------------------------------------------------------------------MAMNAGING MEMEBER - FIANL--------------------------------------------------------------------------------------------------------//
    //FINISH
    public void updateUser(com.mycompany.mylibrarysys.model.User user) {
        try {
            conn.getDBConnect();
            String sql = "UPDATE user_reg SET firstname = ?, lastname = ?, email_address = ?, contact_number = ?, user_password = ?, username = ? WHERE userid = ?";
            java.sql.PreparedStatement ps = conn.getConnect().prepareStatement(sql);

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            ps.setString(1, user.getFirstname());
            ps.setString(2, user.getLastname());
            ps.setString(3, user.getEmailAddress());
            ps.setString(4, user.getContactNumber());
            ps.setString(5, hashedPassword);
            ps.setString(6, user.getUsername());
            ps.setInt(7, user.getUserId());

            int rowsUpdated = ps.executeUpdate();
            ps.close();
            conn.closeConnection();

            if (rowsUpdated > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("User update failed or no changes made.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //FINISH
    public void deleteUser(com.mycompany.mylibrarysys.model.User user) {
        try {
            conn.getDBConnect();
            java.sql.PreparedStatement ps = conn.getConnect().prepareStatement("DELETE FROM user_reg WHERE userid = ?");
            ps.setInt(1, user.getUserId());
            ps.execute();
            ps.close();
            conn.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //CHANGE PASSWORD
    //FINISH
    public void changePassword(com.mycompany.mylibrarysys.model.User user) {
        try {
            conn.getDBConnect();
            String sql = "UPDATE user_reg SET user_password = ? WHERE userid = ?";
            java.sql.PreparedStatement ps = conn.getConnect().prepareStatement(sql);

            String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

            ps.setString(1, hashedPassword);
            ps.setInt(2, user.getUserId());

            int rowsUpdated = ps.executeUpdate();
            ps.close();
            conn.closeConnection();

            if (rowsUpdated > 0) {
                System.out.println("User password successfully changed.");
            } else {
                System.out.println("User password change failed or no changes made.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///-------------------------------------------------BOOK MANAGEMENT----------------------------------------------------------------------------------------------------------------------//
    //ADD BOOK: FINAL
    //FINISH
    public void addBook(com.mycompany.mylibrarysys.model.Book book) {
        conn.getDBConnect();
        Connection connection = conn.getConnect();

        String checkBookQuery = "SELECT book_id FROM books WHERE book_isbn = ?";
        String insertBookQuery = "INSERT INTO books (book_name, book_author, book_isbn, book_genre, book_status, book_copies) VALUES (?, ?, ?, ?, ?, ?) RETURNING book_id";
        String inventoryUpdateQuery = "UPDATE inventory SET total_copies = books.book_copies FROM books WHERE inventory.book_id = books.book_id AND inventory.book_id = ?";
        String insertCopiesQuery = "INSERT INTO inventory (book_id, borrowed_copies, available_copies, total_copies) VALUES (?, 0, ?, ?)";
        
        try (
            PreparedStatement checkBookStmt = connection.prepareStatement(checkBookQuery); PreparedStatement insertBookStmt = connection.prepareStatement(insertBookQuery, Statement.RETURN_GENERATED_KEYS);PreparedStatement insertCopiesStmt = connection.prepareStatement(insertCopiesQuery);PreparedStatement inventoryUpdateStmt = connection.prepareStatement(inventoryUpdateQuery);) {
            checkBookStmt.setString(1, book.getIsbn());
            ResultSet rs = checkBookStmt.executeQuery();

            int bookId;
            if (rs.next()) {
                // Book exists
                bookId = rs.getInt("book_id");
                
                inventoryUpdateStmt.setInt(1, bookId);
                inventoryUpdateStmt.executeUpdate();
                
            } else {
                insertBookStmt.setString(1, book.getName());
                insertBookStmt.setString(2, book.getAuthor());
                insertBookStmt.setString(3, book.getIsbn());
                insertBookStmt.setString(4, book.getGenre());
                insertBookStmt.setString(5, book.getStatus());
                insertBookStmt.setInt(6, book.getCopies());

                int affectedRows = insertBookStmt.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Failed to insert book.");
                }
                ResultSet bookRs = insertBookStmt.getGeneratedKeys();
                if (bookRs.next()) {
                    bookId = bookRs.getInt(1);
                } else {
                    throw new SQLException("Failed to retrieve book ID.");
                }
                bookRs.close();
                
                insertCopiesStmt.setInt(1, bookId);
                insertCopiesStmt.setInt(2, book.getCopies()); // available_copies = total_copies
                insertCopiesStmt.setInt(3, book.getCopies()); // total_copies
                insertCopiesStmt.executeUpdate();
                
            }
            rs.close();
          
        } catch (SQLException e) {
            System.err.println("Error adding book: " + e.getMessage());
            e.printStackTrace();
        }
    }

    //UPDATING BOOK - FINAL
    //FINISH
    public void updateBook(com.mycompany.mylibrarysys.model.Book book) {
        try {
            conn.getDBConnect();
            String sql = "UPDATE books SET book_name = ?, book_author = ?, book_isbn = ?, book_genre = ?, book_status = ?, book_copies =? WHERE book_id = ?";
            java.sql.PreparedStatement ps = conn.getConnect().prepareStatement(sql);

            ps.setString(1, book.getName());
            ps.setString(2, book.getAuthor());
            ps.setString(3, book.getIsbn());
            ps.setString(4, book.getGenre());
            ps.setString(5, book.getStatus());
            ps.setInt(6, book.getCopies());
            // Setting the book ID in the WHERE clause
            ps.setInt(7, book.getId());

            int rowsUpdated = ps.executeUpdate();
            ps.close();
            conn.closeConnection();

            if (rowsUpdated > 0) {
                System.out.println("Book updated successfully.");
            } else {
                System.out.println("Book update failed or no changes made.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //FINISH
    public void deleteBook(com.mycompany.mylibrarysys.model.Book book) {
        try {
            conn.getDBConnect();
            Utility util = new Utility();
            // Check if the book has borrowed copies
            String checkQuery = "SELECT borrowed_copies FROM inventory WHERE book_id = ?";
            PreparedStatement checkStmt = conn.getConnect().prepareStatement(checkQuery);
            checkStmt.setInt(1, book.getId());
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next() && rs.getInt("borrowed_copies") > 0) {
                // Show alert if books are still borrowed
                util.showAlert("Error", "This book cannot be deleted because not all copies have been returned.", Alert.AlertType.ERROR);
                rs.close();
                checkStmt.close();
                conn.closeConnection();
                return;
            }

            rs.close();
            checkStmt.close();

            // Proceed with deletion if no borrowed copies
            String deleteQuery = "DELETE FROM books WHERE book_id = ?";
            PreparedStatement deleteStmt = conn.getConnect().prepareStatement(deleteQuery);
            deleteStmt.setInt(1, book.getId());
            deleteStmt.execute();
            deleteStmt.close();

            conn.closeConnection();

            // Show confirmation alert
            util.showAlert("Sucess", "The book has been successfully deleted.", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //BORROW BOOKS - FINAL
    public void borrowBook(int bookId) {
        String statusQuery = "UPDATE books SET book_status = 'Unavailable' WHERE book_id = ? AND (SELECT available_copies FROM inventory WHERE book_id = ?) = 0";
        String checkQuery = "SELECT available_copies FROM inventory WHERE book_id = ?";
        String updateQuery = "UPDATE inventory SET available_copies = available_copies - 1 WHERE book_id = ? AND available_copies > 0";
        String updateBorrowQuery = "UPDATE inventory SET borrowed_copies = total_copies - available_copies WHERE book_id = ?";
        
        conn.getDBConnect();
        Connection connection = conn.getConnect();

        try (
                PreparedStatement checkStmt = connection.prepareStatement(checkQuery); PreparedStatement updateStmt = connection.prepareStatement(updateQuery); PreparedStatement borrowStmt = connection.prepareStatement(updateBorrowQuery); PreparedStatement statusStmt = connection.prepareStatement(statusQuery)) {
            // Check current available copies
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int copies = rs.getInt("available_copies");
                // Close the ResultSet after fetching the value
                rs.close();

                if (copies > 0) {
                    // Subtract 1 from available copies
                    updateStmt.setInt(1, bookId);
                    updateStmt.executeUpdate();
                    
                  

                    // Update borrowed copies count
                    borrowStmt.setInt(1, bookId);
                    borrowStmt.executeUpdate();

                    // Update status if no copies left
                    checkStmt.setInt(1, bookId);
                    ResultSet rsCheck = checkStmt.executeQuery();
                    if (rsCheck.next() && rsCheck.getInt("available_copies") == 0) {
                        statusStmt.setInt(1, bookId);
                        statusStmt.setInt(2, bookId);
                        statusStmt.executeUpdate();
                    }
                    // Close ResultSet after rechecking availability
                    rsCheck.close();

                    System.out.println("Book borrowed successfully!");
                } else {
                    System.out.println("No copies available to borrow.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    public void insertBookBorrowed(int userId, int bookId, String bookName) {
        String query = "INSERT INTO borrowed_books (user_id, book_id, book_name, issue_date) VALUES (?, ?, ?, NOW())";

        conn.getDBConnect();
        Connection connection = conn.getConnect();
        try (
                PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, userId);
            stmt.setInt(2, bookId);
            stmt.setString(3, bookName);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Product borrowed successfully.");
            } else {
                System.out.println("Failed to borrow the product.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //GET USER LIST: FINAL
    //FINISH
    public ObservableList<com.mycompany.mylibrarysys.model.User> getUserList() {
        ObservableList<com.mycompany.mylibrarysys.model.User> userList = FXCollections.observableArrayList();
        try {
            String query = "select userid, firstname, lastname, email_address, contact_number, user_password, username from user_reg order by firstname asc";
            conn.getDBConnect();
            Statement stmnt = conn.getConnect().createStatement();
            ResultSet result = stmnt.executeQuery(query);
            com.mycompany.mylibrarysys.model.User user;
            while (result.next()) {
                user = new com.mycompany.mylibrarysys.model.User(result.getInt("userid"), result.getString("firstname"), result.getString("lastname"), result.getString("email_address"), result.getString("contact_number"), result.getString("user_password"), result.getString("username"));
                userList.add(user);
            }
            result.close();
            stmnt.close();
            conn.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
    
    
    //GET BOOK LIST: FINAL
    /*
    public ObservableList<com.mycompany.mylibrarysys.model.Book> getBookListTwo() {
        ObservableList<com.mycompany.mylibrarysys.model.Book> bookList = FXCollections.observableArrayList();
        String query = "SELECT b.book_id, b.book_name, b.book_author, b.book_isbn, b.book_genre, b.book_status, "
                + "COALESCE(i.available_copies, 0) AS available_copies "
                + "FROM books b LEFT JOIN inventory i ON b.book_id = i.book_id";
        conn.getDBConnect();
        Connection connection = conn.getConnect();

        try (
                PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                String name = rs.getString("book_name");
                String author = rs.getString("book_author");
                String isbn = rs.getString("book_isbn");
                String genre = rs.getString("book_genre");
                String status = rs.getString("book_status");
                int availableCopies = rs.getInt("available_copies");

                // If totalCopies is 0, update status to "Unavailable"
                if (availableCopies == 0) {
                    status = "Unavailable";
                }

                Book book = new Book(bookId, name, author, isbn, genre, status);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }
    */
    
    
    //FINISH
      public ObservableList<com.mycompany.mylibrarysys.model.Book> getCruBookList() {
        ObservableList<com.mycompany.mylibrarysys.model.Book> bookList = FXCollections.observableArrayList();
        
        try{
        String query = "SELECT book_id, book_name, book_author, book_isbn, book_genre, book_status, book_copies FROM books order by book_author asc";
        conn.getDBConnect();
         Statement stmnt = conn.getConnect().createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            com.mycompany.mylibrarysys.model.Book book;

            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                String name = rs.getString("book_name");
                String author = rs.getString("book_author");
                String isbn = rs.getString("book_isbn");
                String genre = rs.getString("book_genre");
                String status = rs.getString("book_status");
                int copies = rs.getInt("book_copies");
                
                book = new Book(bookId, name, author, isbn, genre, status, copies);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }


    // GET BOOK LIST: FINAL (excluding borrowed books)
    public ObservableList<com.mycompany.mylibrarysys.model.Book> getBookList() {
    ObservableList<com.mycompany.mylibrarysys.model.Book> bookList = FXCollections.observableArrayList();
    try{
        
   String query = "SELECT b.book_id, b.book_name, b.book_author, b.book_isbn, b.book_genre, " +
                       "b.book_status, b.book_copies, i.available_copies " +
                       "FROM books b " +
                       "JOIN inventory i ON b.book_id = i.book_id " +
                       "ORDER BY b.book_author ASC";
   
    conn.getDBConnect();
    Connection connection = conn.getConnect();
    
         Statement stmnt = conn.getConnect().createStatement();
            ResultSet rs = stmnt.executeQuery(query);
            com.mycompany.mylibrarysys.model.Book book;

            while (rs.next()) {
                int bookId = rs.getInt("book_id");
                String name = rs.getString("book_name");
                String author = rs.getString("book_author");
                String isbn = rs.getString("book_isbn");
                String genre = rs.getString("book_genre");
                String status = rs.getString("book_status");
                int copies = rs.getInt("book_copies");
                int availableCopies = rs.getInt("available_copies");

                if (availableCopies == 0 && !"Unavailable".equalsIgnoreCase(status)) {
                String updateStatusQuery = "UPDATE books SET book_status = 'Unavailable' WHERE book_id = ?";
                try (PreparedStatement updateStmt = connection.prepareStatement(updateStatusQuery)) {
                    updateStmt.setInt(1, bookId);
                    updateStmt.executeUpdate();
                    status = "Unavailable"; // Also reflect it in the current object
                }
            } 
                
                if (availableCopies > 0 && "Unavailable".equalsIgnoreCase(status)) {
                String updateStatusQuery = "UPDATE books SET book_status = 'Available' WHERE book_id = ?";
                try (PreparedStatement updateStmt = connection.prepareStatement(updateStatusQuery)) {
                    updateStmt.setInt(1, bookId);
                    updateStmt.executeUpdate();
                    status = "Available"; // Also reflect it in the current object
                }
            } 
                
                book = new Book(bookId, name, author, isbn, genre, status, copies);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookList;
    }

    //FINAL
    //FINISHED    
    public ObservableList<BorrowedBooks> getBorrowedBooksByUser(int userId) {
        String query = "SELECT id, user_id, book_id, book_name, issue_date, return_date, date_returned, days_overdue, fee "
                + "FROM borrowed_books WHERE user_id = ? AND date_returned IS NULL ORDER BY issue_date DESC";

        ObservableList<BorrowedBooks> borrowedBooks = FXCollections.observableArrayList();

        conn.getDBConnect();
        Connection connection = conn.getConnect();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BorrowedBooks borrowedBook = new BorrowedBooks(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getInt("book_id"),
                            rs.getString("book_name"),
                            rs.getDate("issue_date"),
                            rs.getDate("return_date"),
                            rs.getDate("date_returned"),
                            rs.getInt("days_overdue"),
                            rs.getInt("fee")
                    );
                    borrowedBooks.add(borrowedBook);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowedBooks;
    }

    /*
    public ObservableList<BorrowedBooks> getBorrowedBooksByUser(int userId) {
        String query = "SELECT id, user_id, book_id, book_name, issue_date, return_date, date_returned, days_overdue, fee "
                + "FROM borrowed_books WHERE user_id = ? ORDER BY issue_date DESC";

        ObservableList<BorrowedBooks> borrowedBooks = FXCollections.observableArrayList();

        conn.getDBConnect();
        Connection connection = conn.getConnect();

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    BorrowedBooks borrowedBook = new BorrowedBooks(
                            rs.getInt("id"),
                            rs.getInt("user_id"),
                            rs.getInt("book_id"),
                            rs.getString("book_name"),
                            rs.getDate("issue_date"),
                            rs.getDate("return_date"),
                            rs.getDate("date_returned"),
                            rs.getInt("days_overdue"),
                            rs.getInt("fee")
                    );
                    borrowedBooks.add(borrowedBook);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return borrowedBooks;
    }*/
    //FINAL
    
    /*
    public void processReturnBook(int bookId, int borrowedBookId) {
        String checkCopiesQuery = "SELECT available_copies, total_copies FROM inventory WHERE book_id = ?";
        String incrementQuery = "UPDATE inventory SET available_copies = available_copies + 1 WHERE book_id = ?";
        String updateStatusQuery = "UPDATE books SET book_status = 'Available' WHERE book_id = ? AND book_status = 'Unavailable'";
        String updateBorrowQuery = "UPDATE inventory SET borrowed_copies = total_copies - available_copies WHERE book_id = ?";
        String updateReturnQuery = "UPDATE borrowed_books SET date_returned = CURRENT_DATE, "
                + "days_overdue = GREATEST(CURRENT_DATE - return_date, 0), "
                + " \"returned?\" = 'true',"
                + "fee = GREATEST((CURRENT_DATE - return_date), 0) * 35 "
                + "WHERE id = ?";
       

        conn.getDBConnect();
        Connection connection = conn.getConnect();

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement checkStmt = connection.prepareStatement(checkCopiesQuery);
                PreparedStatement incrementStmt = connection.prepareStatement(incrementQuery);
                PreparedStatement borrowStmt = connection.prepareStatement(updateBorrowQuery);
                PreparedStatement statusStmt = connection.prepareStatement(updateStatusQuery);
               PreparedStatement returnStmt = connection.prepareStatement(updateReturnQuery)) {

                // Step 1: Check if return is valid
                checkStmt.setInt(1, bookId);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                     
                    int availableCopies = rs.getInt("available_copies");
                    int totalCopies = rs.getInt("total_copies");

                    if (availableCopies >= totalCopies) {
                        System.out.println("Return prohibited: All copies already available.");
                        return;
                    }
                } else {
                    System.out.println("Book ID not found in inventory.");
                    return;
                }

                // Step 2: Update borrowed_books table
                returnStmt.setInt(1, borrowedBookId);
                int returnRows = returnStmt.executeUpdate();
                if (returnRows > 0) {
                    System.out.println("Return record updated in borrowed_books.");
                } else {
                    System.out.println("Failed to update borrowed_books. Check ID.");
                    return;
                }

                // Step 3: Increment available copies
                incrementStmt.setInt(1, bookId);
                incrementStmt.executeUpdate();

                // Step 4: Update borrowed copies
                borrowStmt.setInt(1, bookId);
                borrowStmt.executeUpdate();

                // Step 5: Set status to Available
                statusStmt.setInt(1, bookId);
                statusStmt.executeUpdate();

                connection.commit();
                System.out.println("Book returned successfully and inventory updated.");

            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

*/
    
    public void updateUserRecord(int userId, double fee) {
    String updateQuery = "UPDATE record SET borrow_count = borrow_count + 1, total_fee = total_fee + ? WHERE user_id = ?";
    String insertQuery = "INSERT INTO record (user_id, borrow_count, total_fee) SELECT ?, 1, ? WHERE NOT EXISTS (SELECT 1 FROM record WHERE user_id = ?)";

    
            conn.getDBConnect();
        Connection connection = conn.getConnect();
    try  {
        PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
        updateStmt.setDouble(1, fee);
        updateStmt.setInt(2, userId);
        updateStmt.executeUpdate();

        PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
        insertStmt.setInt(1, userId);
        insertStmt.setDouble(2, fee);
        insertStmt.setInt(3, userId);
        insertStmt.executeUpdate();
    } 
    catch (SQLException e) {
        e.printStackTrace();
    }

}
    
    public void processReturnBook(int bookId, int borrowedBookId) {
    String checkCopiesQuery = "SELECT available_copies, total_copies FROM inventory WHERE book_id = ?";
    String incrementQuery = "UPDATE inventory SET available_copies = available_copies + 1 WHERE book_id = ?";
    String updateStatusQuery = "UPDATE books SET book_status = 'Available' WHERE book_id = ? AND book_status = 'Unavailable'";
    String updateBorrowQuery = "UPDATE inventory SET borrowed_copies = total_copies - available_copies WHERE book_id = ?";
    String updateReturnQuery = "UPDATE borrowed_books SET date_returned = CURRENT_DATE, "
            + "days_overdue = GREATEST(CURRENT_DATE - return_date, 0), "
            + "\"returned?\" = 'true', "
            + "fee = GREATEST((CURRENT_DATE - return_date), 0) * 35 "
            + "WHERE id = ?";
    String fetchFeeUserQuery = "SELECT user_id, fee FROM borrowed_books WHERE id = ?";

    conn.getDBConnect();
    Connection connection = conn.getConnect();

    if (connection == null) {
        System.out.println("Database connection failed.");
        return;
    }

    try {
        connection.setAutoCommit(false);

        try (
            PreparedStatement checkStmt = connection.prepareStatement(checkCopiesQuery);
            PreparedStatement incrementStmt = connection.prepareStatement(incrementQuery);
            PreparedStatement borrowStmt = connection.prepareStatement(updateBorrowQuery);
            PreparedStatement statusStmt = connection.prepareStatement(updateStatusQuery);
            PreparedStatement returnStmt = connection.prepareStatement(updateReturnQuery);
            PreparedStatement fetchFeeUserStmt = connection.prepareStatement(fetchFeeUserQuery)
        ) {
            // Step 1: Check current inventory status
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                System.out.println("Book ID not found in inventory.");
                return;
            }

            int availableCopies = rs.getInt("available_copies");
            int totalCopies = rs.getInt("total_copies");

            if (availableCopies >= totalCopies) {
                System.out.println("Return not processed: All copies already available.");
                return;
            }

            // Step 2: Update borrowed_books table with return date and calculate fee
            returnStmt.setInt(1, borrowedBookId);
            int updatedReturn = returnStmt.executeUpdate();
            if (updatedReturn == 0) {
                System.out.println("Return failed: Invalid borrowed_book ID.");
                return;
            }

            // Step 3: Fetch fee and user ID
            fetchFeeUserStmt.setInt(1, borrowedBookId);
            ResultSet userFeeRs = fetchFeeUserStmt.executeQuery();

            if (userFeeRs.next()) {
                int userId = userFeeRs.getInt("user_id");
                double fee = userFeeRs.getDouble("fee");
                updateUserRecord(userId, fee); // Step 4: Apply fee to user's record
            }

            // Step 5: Update inventory and book status
            incrementStmt.setInt(1, bookId);
            incrementStmt.executeUpdate();

            borrowStmt.setInt(1, bookId);
            borrowStmt.executeUpdate();

            statusStmt.setInt(1, bookId);
            statusStmt.executeUpdate();

            connection.commit();
            System.out.println("Book return processed successfully.");

        } catch (SQLException e) {
            connection.rollback();
            System.err.println("Transaction failed. Rolled back.");
            e.printStackTrace();
        } finally {
            connection.setAutoCommit(true);
        }

    } catch (SQLException e) {
        System.err.println("Failed to process return due to connection issue.");
        e.printStackTrace();
    }
}

}