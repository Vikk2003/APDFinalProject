package view;

import controller.NavigationController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.Modality;

import java.util.HashMap;

public class LoginView {

    private NavigationController navigationController;
    private HashMap<String, String> userDatabase; // Simulated database for storing credentials

    public LoginView(NavigationController navigationController) {
        this.navigationController = navigationController;
        this.userDatabase = new HashMap<>();
    }

    public Scene getLoginScene() {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Welcome! Log in or Create an Account");

        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");
        Button exitButton = new Button("Exit");

        // Login Button Event
        loginButton.setOnAction(e -> showLoginDialog());

        // Register Button Event
        registerButton.setOnAction(e -> showRegisterDialog());

        // Exit Button Event
        exitButton.setOnAction(e -> System.exit(0));

        layout.getChildren().addAll(welcomeLabel, loginButton, registerButton, exitButton);

        return new Scene(layout, 300, 200);
    }

    // Register Dialog
    private void showRegisterDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Register New Account");

        VBox dialogLayout = new VBox(10);
        dialogLayout.setPadding(new Insets(20));
        dialogLayout.setAlignment(Pos.CENTER);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setPromptText("Confirm your password");

        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        // OK Button Event
        okButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();

            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showAlert("Error", "All fields must be filled.");
            } else if (!password.equals(confirmPassword)) {
                showAlert("Error", "Passwords do not match.");
            } else if (userDatabase.containsKey(username)) {
                showAlert("Error", "Username already exists.");
            } else {
                userDatabase.put(username, password); // Save credentials
                showAlert("Success", "Account registered successfully!");
                dialog.close();
            }
        });

        // Cancel Button Event
        cancelButton.setOnAction(e -> dialog.close());

        dialogLayout.getChildren().addAll(
                new Label("Create a new account"),
                usernameField, passwordField, confirmPasswordField,
                new HBox(10, okButton, cancelButton)
        );

        Scene dialogScene = new Scene(dialogLayout, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    // Login Dialog
    private void showLoginDialog() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Login");

        VBox dialogLayout = new VBox(10);
        dialogLayout.setPadding(new Insets(20));
        dialogLayout.setAlignment(Pos.CENTER);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        // OK Button Event
        okButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
                showAlert("Success", "Login successful!");
                dialog.close();
                navigationController.showDashboard(); // Navigate to the Dashboard
            } else {
                showAlert("Error", "Invalid username or password.");
            }
        });

        // Cancel Button Event
        cancelButton.setOnAction(e -> dialog.close());

        dialogLayout.getChildren().addAll(
                new Label("Log in to your account"),
                usernameField, passwordField,
                new HBox(10, okButton, cancelButton)
        );

        Scene dialogScene = new Scene(dialogLayout, 300, 200);
        dialog.setScene(dialogScene);
        dialog.showAndWait();
    }

    // Alert Dialog Helper
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
