package model;
import controller.NavigationController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(@SuppressWarnings("exports") Stage primaryStage) {
        NavigationController navigationController = new NavigationController(primaryStage);
        navigationController.showLoginView(); // Start with Login View
    }

    public static void main(String[] args) {
        launch(args);
    }
}
