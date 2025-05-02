package com.mycompany.mylibrarysys;

import com.mycompany.mylibrarysys.model.Book;
import com.mycompany.mylibrarysys.model.BorrowedBooks;
import com.mycompany.mylibrarysys.model.User;
import com.mycompany.mylibrarysys.model.Utility;
import com.mycompany.mylibrarysys.model.UserSession;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MemDashboardController implements Initializable {
    
    
    @FXML
    private TableColumn<BorrowedBooks, Integer> feesClmRtn;

    @FXML
    private TableColumn<BorrowedBooks, Date> issueDateClmRtn;

    @FXML
    private TableColumn<BorrowedBooks, Date> returnDateClmRtn;

    @FXML
    private TableColumn<BorrowedBooks, Date> returnedDateClmRtn;

    @FXML
    private TableColumn<BorrowedBooks, Integer> daysOverdueClmRtn;


    @FXML
    private BorderPane addBookForm;

    @FXML
    private AnchorPane addBookMenuAnchor;

    @FXML
    private TextField addBookSearch;

    @FXML
    private TableView<Book> addBookTableV;

    @FXML
    private BorderPane addMemberForm;

    @FXML
    private AnchorPane addingBookAnchr;

    @FXML
    private TextField authorAddTxt;

    @FXML
    private TableColumn<Book, String> authorClmAdd;

    @FXML
    private TableColumn<Book, String> authorClmBrw;

    @FXML
    private TableColumn<BorrowedBooks, String> authorClmRtn;

    @FXML
    private Label authorLblBrw;

    @FXML
    private Label authorLblRtn;

    @FXML
    private TableColumn<BorrowedBooks, Integer> bookIdClmRtn;

    @FXML
    private AnchorPane borrowBookAnchr;

    @FXML
    private BorderPane borrowBookForm;

    @FXML
    private AnchorPane borrowMenuAnchor;

    @FXML
    private TextField borrowSearch;

    @FXML
    private Button btnBooksAdd;

    @FXML
    private Button btnBooksBrw;

    @FXML
    private Button btnBooksMem;

    @FXML
    private Button btnBooksProf;

    @FXML
    private Button btnBooksRtn;

    @FXML
    private Button btnBorrow;

    @FXML
    private Button btnBorrowAdd;

    @FXML
    private Button btnBorrowBrw;

    @FXML
    private Button btnBorrowMem;

    @FXML
    private Button btnBorrowProf;

    @FXML
    private Button btnBorrowRtn;

    @FXML
    private Button btnDeleteAdd;

    @FXML
    private Button btnMemDlt;

    @FXML
    private Button btnMemNew;

    @FXML
    private Button btnMemSave;

    @FXML
    private Button btnMemUpdate;

    @FXML
    private Button btnMembersAdd;

    @FXML
    private Button btnMembersBrw;

    @FXML
    private Button btnMembersMem;

    @FXML
    private Button btnMembersProf;

    @FXML
    private Button btnMembersRtn;

    @FXML
    private Button btnNewAdd;

    @FXML
    private Button btnProfileAdd;

    @FXML
    private Button btnProfileBrw;

    @FXML
    private Button btnProfileMem;

    @FXML
    private Button btnProfileProf;

    @FXML
    private Button btnProfileRtn;

    @FXML
    private Button btnReturn;

    @FXML
    private Button btnReturnAdd;

    @FXML
    private Button btnReturnBrw;

    @FXML
    private Button btnReturnMem;

    @FXML
    private Button btnReturnProf;

    @FXML
    private Button btnReturnRtn;

    @FXML
    private Button btnSaveAdd;

    @FXML
    private Button btnSignoutAdd;

    @FXML
    private Button btnSignoutBrw;

    @FXML
    private Button btnSignoutMem;

    @FXML
    private Button btnSignoutProf;

    @FXML
    private Button btnSignoutRtn;

    @FXML
    private Button btnUpdateAdd;

    @FXML
    private Button btnUpdateProf;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private PasswordField confirmPasswordProf;

    @FXML
    private Label copiesLblBrw;

    @FXML
    private Label copiesLblRtn;

    @FXML
    private TextField emailTxtProf;

    @FXML
    private ComboBox<String> genreAddCombx;

    @FXML
    private TableColumn<Book, String> genreClmAdd;

    @FXML
    private TableColumn<Book, String> genreClmBrw;

    @FXML
    private TableColumn<BorrowedBooks, String> genreClmRtn;

    @FXML
    private Label genreLblBrw;

    @FXML
    private Label genreLblRtn;

    @FXML
    private TableColumn<Book, Integer> idClmAdd;

    @FXML
    private TableColumn<Book, Integer> idClmBrw;

    @FXML
    private TextField isbnAddTxt;

    @FXML
    private TableColumn<Book, String> isbnClmAdd;

    @FXML
    private TableColumn<Book, String> isbnClmBrw;

    @FXML
    private TableColumn<BorrowedBooks, String> isbnClmRtn;

    @FXML
    private Label isbnLblBrw;

    @FXML
    private Label isbnLblRtn;

    @FXML
    private TextField memCntctNumTxt;

    @FXML
    private TableColumn<User, String> memEmailClm;

    @FXML
    private TextField memEmailTxt;

    @FXML
    private TableColumn<User, String> memNameClm;

    @FXML
    private TextField memNameTxt;

    @FXML
    private TableColumn<User, String> memNumberClm;

    @FXML
    private TableColumn<User, String> memSurnameClm;

    @FXML
    private TextField memSurnameTxt;
    
    
    @FXML
    private TextField memUsernameTxt;

    @FXML
    private TableColumn<User, String> memUsernameClm;

    @FXML
    private TableColumn<User, Integer> memberIdClm;

    @FXML
    private AnchorPane memberMenuAnchor;

    @FXML
    private TextField memberSearch;

    @FXML
    private TextField nameTxtProf;

    @FXML
    private TextField numberTxtProf;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField passwordProf;

    @FXML
    private BorderPane profileForm;

    @FXML
    private AnchorPane profileMenuAnchor;

    @FXML
    private AnchorPane returnBookAnchr;

    @FXML
    private BorderPane returnBookForm;

    @FXML
    private AnchorPane returnMenuAnchor;

    @FXML
    private TextField returnSearch;

    @FXML
    private ComboBox<String> statusAddCombx;

    @FXML
    private TableColumn<Book, String> statusClmAdd;

    @FXML
    private TableColumn<Book, String> statusClmBrw;

    @FXML
    private TableColumn<BorrowedBooks, String> statusClmRtn;

    @FXML
    private Label statusLblBrw;

    @FXML
    private Label statusLblRtn;

    @FXML
    private TextField surnameTxtProf;

    @FXML
    private TableView<Book> tableViewBrw;

    @FXML
    private TableView<BorrowedBooks> tableViewRtn;

    @FXML
    private TextField titleAddTxt;

    @FXML
    private TableColumn<Book, String> titleClmAdd;

    @FXML
    private TableColumn<Book, String> titleClmBrw;

    @FXML
    private TableColumn<BorrowedBooks, String> titleClmRtn;

    @FXML
    private Label titleLblBrw;

    @FXML
    private Label titleLblRtn;

    @FXML
    private TableView<User> userTablelV;

    @FXML
    private Label usernameLblAdd;

    @FXML
    private Label usernameLblBrw;

    @FXML
    private Label usernameLblMem;

    @FXML
    private Label usernameLblProf;

    @FXML
    private Label usernameLblRtn;
    
    private String username; 
    
    private UserAccessController userCtrl = new UserAccessController();

    private Book book;

    private User user;

    private Utility util = new Utility();
       
    private BorrowedBooks borrowed;

    private static Stage stage;

    /**
     * @return the stage
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * @param aStage the stage to set
     */
    public static void setStage(Stage aStage) {
        stage = aStage;
    }

    //----------------------------------------------------------------------------------------------------------------------------------------------------------//
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hideMenus();
       

        showBooks();
        
     
        
        borrowSearch.textProperty().addListener((observableList, oldValue, newValue) -> {
            filterBook(newValue);
        });

         returnSearch.textProperty().addListener((observableList, oldValue, newValue) -> {
            filterBook(newValue);
        });

    }

    //----------------------------------------------------------------SWITCHING FORMS - FINAL----------------------------------------------------------------------------------------------------------------------//
    public void switchForm(ActionEvent event) {

        if (event.getSource() == btnProfileAdd || event.getSource() == btnProfileMem || event.getSource() == btnProfileBrw || event.getSource() == btnProfileRtn || event.getSource() == btnProfileProf) {

            profileForm.setVisible(true);
            borrowBookForm.setVisible(false);
            returnBookForm.setVisible(false);

        } else if (event.getSource() == btnMembersAdd || event.getSource() == btnMembersMem || event.getSource() == btnMembersBrw || event.getSource() == btnMembersRtn || event.getSource() == btnMembersProf) {

           
            profileForm.setVisible(false);
            borrowBookForm.setVisible(false);
            returnBookForm.setVisible(false);

        } else if (event.getSource() == btnBooksAdd || event.getSource() == btnBooksMem || event.getSource() == btnBooksBrw || event.getSource() == btnBooksRtn || event.getSource() == btnBooksProf) {

            
            profileForm.setVisible(false);
            addMemberForm.setVisible(false);
            borrowBookForm.setVisible(false);
            returnBookForm.setVisible(false);

        } else if (event.getSource() == btnBorrowAdd || event.getSource() == btnBorrowMem || event.getSource() == btnBorrowBrw || event.getSource() == btnBorrowRtn || event.getSource() == btnBorrowProf) {

            borrowBookForm.setVisible(true);
            profileForm.setVisible(false);
            returnBookForm.setVisible(false);

        } else if (event.getSource() == btnReturnAdd || event.getSource() == btnReturnMem || event.getSource() == btnReturnBrw || event.getSource() == btnReturnRtn || event.getSource() == btnReturnProf) {

            returnBookForm.setVisible(true);
            profileForm.setVisible(false);
            borrowBookForm.setVisible(false);

        } else if (event.getSource() == btnSignoutAdd || event.getSource() == btnSignoutMem || event.getSource() == btnSignoutBrw || event.getSource() == btnSignoutRtn || event.getSource() == btnSignoutProf) {

            userCtrl.openNewWindow("UserAccess.fxml");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }

    }

    //------------------------------HIDE MENU - FINAL---------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    public void hideMenus() {
        profileForm.setVisible(false);
        borrowBookForm.setVisible(true);
        returnBookForm.setVisible(false);
    }

    //--------------------------SHOW BOOKS AND USERS IN THE TABLE VIEWS - FINAL------------------------------------------------------------------------------//
    /*@FXML
    private void showBooks() {

        //util.populateBookTable(tableViewBrw, idClmBrw, titleClmBrw, authorClmBrw, isbnClmBrw, genreClmBrw, statusClmBrw);
        UserSession session = new UserSession();
        int userId = session.getLoggedInUserId();
        System.out.println("Logged User ID(showBooks): " + userId);
         util.populateBookTable(tableViewBrw, idClmBrw, titleClmBrw, authorClmBrw, isbnClmBrw, genreClmBrw, statusClmBrw);
        //util.populateBorrowedBooksTable(tableViewRtn, bookIdClmRtn, titleClmRtn, issueDateClmRtn, returnDateClmRtn, returnedDateClmRtn, daysOverdueClmRtn, feesClmRtn, userId);

    }*/
    
    //--------------------------SHOW BOOKS AND USERS IN THE TABLE VIEWS - FINAL------------------------------------------------------------------------------//
    @FXML
    private void showBooks() {

        //util.populateBookTable(tableViewBrw, idClmBrw, titleClmBrw, authorClmBrw, isbnClmBrw, genreClmBrw, statusClmBrw);
        UserSession session = new UserSession();
        int userId = session.getLoggedInUserId();
        System.out.println("Logged User ID(showBooks): " + userId);
        util.populateBorrowBookTable(tableViewBrw, idClmBrw, titleClmBrw, authorClmBrw, isbnClmBrw, genreClmBrw, statusClmBrw);
        util.populateBorrowedBooksTable(tableViewRtn, bookIdClmRtn, titleClmRtn, issueDateClmRtn, returnDateClmRtn, returnedDateClmRtn, daysOverdueClmRtn, feesClmRtn, userId);
        //util.populateCrucBookTable(addBookTableV, idClmAdd, titleClmAdd, authorClmAdd, isbnClmAdd, genreClmAdd, statusClmAdd);
        //util.populateCrucBookTable(addBookTableV, idClmAdd, titleClmAdd, authorClmAdd, isbnClmAdd, genreClmAdd, statusClmAdd, copiesClmAdd);

    }

  
    //------------------------------------ADDING NEW BOOK - FINAL-------------------------------------------------------------------------------------//
    @FXML
    private void clickNew() {
        btnUpdateAdd.setDisable(true);
        btnDeleteAdd.setDisable(true);
        btnSaveAdd.setDisable(false);
        genreAddCombx.setValue("Fiction");
        clearField();
    }

    //-----------------------------------CLEAR FIELDS WHEN ADDING A BOOK BUTTON IS CLICKED - FINAL-----------------------------------------------------------------------------------------------------------------//
    private void clearField() {
        titleAddTxt.setText("");
        authorAddTxt.setText("");
        isbnAddTxt.setText("");
        genreAddCombx.setValue("");
        statusAddCombx.setValue("");

    }
    
    private void clearFieldTwo() {
        memNameTxt.setText("");
        memSurnameTxt.setText("");
        memEmailTxt.setText("");
        memCntctNumTxt.setText("");

    }

    //-----------------------------FILTER BOOK AND USERS ON THE TABLE VIEWS - FINAL---------------------------------------------------------------------------------------------------------------------------------------------------------------------------//
    @FXML
    private void filterBook(String searchName) {
        ObservableList<Book> filteredBooks = util.filterBooks(searchName);
        addBookTableV.setItems(filteredBooks);
    }


    //--------------------CLICKING THE TABLE VIEWS - FINAL----------------------------------------------------------------------------------------------------------------------------//

    
    @FXML
    public void mouseClickedTwo(MouseEvent click) {
        try {
            Book book = tableViewBrw.getSelectionModel().getSelectedItem();
            book = new Book(book.getId(), book.getName(), book.getAuthor(), book.getIsbn(), book.getGenre(), book.getStatus(), book.getCopies());
            this.book = book;
            titleLblBrw.setText(book.getName());
            authorLblBrw.setText(book.getAuthor());
            isbnLblBrw.setText(book.getIsbn());
            genreLblBrw.setText(book.getGenre());
            statusLblBrw.setText(book.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    @FXML
    public void mouseClickedBorrow(MouseEvent click) {
        try {
            Book book = tableViewBrw.getSelectionModel().getSelectedItem();
            book = new Book(book.getId(), book.getName(), book.getAuthor(), book.getIsbn(), book.getGenre(), book.getStatus(), book.getCopies());
            this.book = book;
            titleLblBrw.setText(book.getName());
            authorLblBrw.setText(book.getAuthor());
            isbnLblBrw.setText(book.getIsbn());
            genreLblBrw.setText(book.getGenre());
            statusLblBrw.setText(book.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    /*
      @FXML
    public void mouseClickedBorrow(MouseEvent click) {
        try {
            Book book = tableViewBrw.getSelectionModel().getSelectedItem();
            book = new Book(book.getId(), book.getName(), book.getAuthor(), book.getIsbn(), book.getGenre(), book.getStatus(), book.getCopies());
            this.book = book;
            titleLblBrw.setText(book.getName());
            authorLblBrw.setText(book.getAuthor());
            isbnLblBrw.setText(book.getIsbn());
            genreLblBrw.setText(book.getGenre());
            statusLblBrw.setText(book.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /*
    
    
     
        @FXML
    public void mouseClickedBorrowed(MouseEvent click) {
        try {
            BorrowedBooks borrowed = tableViewRtn.getSelectionModel().getSelectedItem();
            borrowed = new BorrowedBooks(borrowed.getId(), borrowed.getUserId(), borrowed.getBookId(), borrowed.getBookName(), borrowed.getIssueDate(), borrowed.getReturnDate(), borrowed.getDateReturned(), borrowed.getDaysOverdue(), borrowed.getFee());
            this.borrowed = borrowed;
            titleLblRtn.setText(borrowed.getBookName());
            //authorLblBrw.setText(borrowed.);
            //isbnLblBrw.setText(borrowed.getIsbn());
            //genreLblBrw.setText(borrowed.getGenre());
            //statusLblBrw.setText(borrowed.getStatus());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    
    //-------------------BORROW BOOK------------------------------------------------------------------------------------------------------------------------//
    @FXML
    private void borrowBook() {
        Book selectedBook = tableViewBrw.getSelectionModel().getSelectedItem();
        util.handleBorrowButton(selectedBook, tableViewBrw.getScene().getWindow());
        UserSession session = new UserSession();
        int userId = session.getLoggedInUserId();
        System.out.println("Logged User ID: " + userId);
        int selectedBookId = selectedBook.getId(); // Get the product ID
        System.out.println("Selected Book ID: " + selectedBookId); // Print it
        String selectedBookName = selectedBook.getName(); // Get the product ID
        System.out.println("Selected Book Name: " + selectedBookName); // Print it

        this.book = selectedBook; // Store the selected produc
        // Now insert userId and selectedProductId into the database using Utility
        
        Utility.insertBookBorrowed(userId, selectedBookId, selectedBookName);
        
        
        showBooks();

    }

    @FXML
    public void handleReturnBook() {
        BorrowedBooks selectedBook = tableViewRtn.getSelectionModel().getSelectedItem();

        System.out.println("Returning books LIB DASHBOARD CONTROLLER");
        if (selectedBook != null) {
            UserSession session = new UserSession();
            int userId = session.getLoggedInUserId();
            int borrowedBookId = selectedBook.getId();
            int bookId = selectedBook.getBookId();

            System.out.println("User ID: " + userId);
            System.out.println("borrowedBookId:" + borrowedBookId);
            System.out.println("bookId: " + bookId);

            Utility.processReturnBook(tableViewRtn, bookId, borrowedBookId, userId);
        } else {
            // Show alert if no book is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Book Selected");
            alert.setContentText("Please select a book to return.");
            alert.showAndWait();
        }
        showBooks();
    }
    
    /*
    
    //-------------------BORROW BOOK------------------------------------------------------------------------------------------------------------------------//
       @FXML
    private void borrowBook() {
        Book selectedBook = tableViewBrw.getSelectionModel().getSelectedItem();
        util.handleBorrowButton(selectedBook, tableViewBrw.getScene().getWindow());
        UserSession session = new UserSession();
            int userId = session.getLoggedInUserId();
            System.out.println("Logged User ID: " + userId);
            int selectedBookId = selectedBook.getId(); // Get the product ID
            System.out.println("Selected Book ID: " + selectedBookId); // Print it
            String selectedBookName = selectedBook.getName(); // Get the product ID
            System.out.println("Selected Book Name: " + selectedBookName); // Print it

            this.book = selectedBook; // Store the selected produc
        // Now insert userId and selectedProductId into the database using Utility
            Utility.insertBookBorrowed(userId, selectedBookId, selectedBookName);
        showBooks();
  
    }
    
    //-------------------RETURN BOOK------------------------------------------------------------------------------------------------------------------------//
   
    
    @FXML
    public void handleReturnBook() {
        BorrowedBooks selectedBook = tableViewRtn.getSelectionModel().getSelectedItem();

        System.out.println("Returning books LIB DASHBOARD CONTROLLER");
        if (selectedBook != null) {
            UserSession session = new UserSession();
            int userId = session.getLoggedInUserId();
            int borrowedBookId = selectedBook.getId();
            int bookId = selectedBook.getBookId();

            System.out.println("borrowedBookId:" + borrowedBookId);
            System.out.println("bookId: " + bookId);

            Utility.processReturnBook(tableViewRtn, bookId, borrowedBookId, userId);
        } else {
            // Show alert if no book is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Book Selected");
            alert.setContentText("Please select a book to return.");
            alert.showAndWait();
        }

    }*/
}




