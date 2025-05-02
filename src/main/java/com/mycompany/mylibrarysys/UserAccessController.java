package com.mycompany.mylibrarysys;

import com.mycompany.mylibrarysys.data.AppQuery;
import com.mycompany.mylibrarysys.data.DBConnection;
import com.mycompany.mylibrarysys.model.User;
import com.mycompany.mylibrarysys.model.UserSession;
import com.mycompany.mylibrarysys.model.Utility;
import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import java.util.Random;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class UserAccessController implements Initializable {

    @FXML
    private Button btnChangePassword;

    @FXML
    private Button btnCreateAcc;

    @FXML
    private Button btnForgotBack;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnLoginRegAcc;

    @FXML
    private Button btnRegLogin;

    @FXML
    private Button btnRegSignup;

    @FXML
    private Button btnRegMem;

    @FXML
    private Button btnRegLib;

    @FXML
    private PasswordField forgotConfirmPassword;

    @FXML
    private Label forgotPassLabel;

    @FXML
    private Hyperlink forgotPassLink;

    @FXML
    private PasswordField forgotPassword;

    @FXML
    private BorderPane forgotPasswordForm;

    @FXML
    private TextField forgotUsername;

    @FXML
    private Label lmsLabel;

    @FXML
    private Label lmsLabel1;

    @FXML
    private BorderPane loginForm;

    @FXML
    private Label loginLabel;

    @FXML
    private PasswordField loginPassword;

    @FXML
    private TextField loginPasswordTxt;

    @FXML
    private TextField loginUsername;

    @FXML
    private TextField regContactNumber;

    @FXML
    private TextField regEmailAddress;

    @FXML
    private TextField regFirstname;

    @FXML
    private Label regLabel;

    @FXML
    private TextField regLastname;

   
    @FXML
    private BorderPane registerForm;

    @FXML
    private Label registerLabel;

    @FXML
    private CheckBox showPasswordCheck;

    private final AppQuery query = new AppQuery();

    private static Stage stage;

    private DBConnection conn = new DBConnection();

    private static Connection connect;

    String username;

    String usernamePrefix = "";
    
    String regPassword;

    Utility util = new Utility();

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

    @FXML
    private void handleRegister(ActionEvent event) {
             
        loginForm.setVisible(true);
        forgotPasswordForm.setVisible(false);
    }

    @FXML
    void switchForm(ActionEvent event) {

        if (event.getSource() == btnRegLogin || event.getSource() == btnForgotBack) {

            loginForm.setVisible(true);
            forgotPasswordForm.setVisible(false);

        } else if (event.getSource() == btnLoginRegAcc) {

            loginForm.setVisible(false);
            forgotPasswordForm.setVisible(false);

        } else if (event.getSource() == forgotPassLink) {

            loginForm.setVisible(false);
            forgotPasswordForm.setVisible(true);
        }

    }

    public void showPassword() {

        if (showPasswordCheck.isSelected()) {

            loginPasswordTxt.setText(loginPassword.getText());
            loginPasswordTxt.setVisible(true);
            loginPassword.setVisible(false);

        } else {

            loginPassword.setText(loginPasswordTxt.getText());
            loginPasswordTxt.setVisible(false);
            loginPassword.setVisible(true);
        }
    }

  

    // Helper method to generate random numbers
    public String generateRandomNumber(int length) {
        Random rand = new Random();
        StringBuilder number = new StringBuilder();
        for (int i = 0; i < length; i++) {
            // Append random digit (0-9)
            number.append(rand.nextInt(10));
        }
        return number.toString();
    }

    @FXML
    private void loginUser() {
        username = loginUsername.getText();
        String password = loginPassword.getText();

        username = username.toUpperCase();
        System.out.println("usrnmae: " + username);

        if (username.isEmpty() || password.isEmpty()) {
            util.showAlert(Alert.AlertType.ERROR, "Error", "Enter username and password!");
            
            System.out.println("empty name: " + username);
            
            System.out.println("empty password: " + password);
            return;
        }
        // Get user ID
        Integer userId = query.getUserId(username, password);
        // Print for debugging
        System.out.println("User ID: " + userId);

        // If login successful
        if (userId != null) {
            util.showAlert(Alert.AlertType.INFORMATION, "Success", "Login Successful!");
            UserSession.setLoggedInUserId(userId);

            if (username.startsWith("ADMIN@")) {
                openNewWindow("LibDashboard_1.fxml");
                closeCurrentWindow();
            } else if (username.startsWith("LIB@")) {

                openNewWindow("LibDashboard.fxml");
                closeCurrentWindow();

            } else if (username.startsWith("MEM@")) {

                openNewWindow("MemDashboard.fxml");
                closeCurrentWindow();

            }
        } else {

            util.showAlert(Alert.AlertType.ERROR, "Error", "Invalid username or password!");

        }

        loginUsername.setText("");
        loginPassword.setText("");
        loginPasswordTxt.setText("");
        loginPassword.setVisible(true);

    }

    public void openNewWindow(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage newStage = new Stage();

            newStage.setScene(new Scene(root));
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            util.showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the new window!");
        }
    }

    // Optionally close the login window
    public void closeCurrentWindow() {
        Stage stage = (Stage) loginUsername.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
