package view;

import controller.NavigationController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

@SuppressWarnings("unused")
public class LoginView {
    private NavigationController navigationController;

    public LoginView(NavigationController navigationController) {
        this.navigationController = navigationController;
    }

    public Scene getLoginScene() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        Label userLabel = new Label("Username:");
        TextField usernameField = new TextField();
        Label passLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> navigationController.showDashboard());

        grid.add(userLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);

        return new Scene(grid, 400, 300);
    }

}
