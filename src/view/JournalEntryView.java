package view;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.JournalEntry;
import controller.NavigationController;

import java.io.File;

public class JournalEntryView {
    private NavigationController navigationController;

    public JournalEntryView(NavigationController navigationController) {
        this.navigationController = navigationController;
    }

    public Scene getJournalEntryScene() {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(10));

        Label titleLabel = new Label("Title:");
        TextField titleField = new TextField();

        Label contentLabel = new Label("Content:");
        TextArea contentArea = new TextArea();

        Label imageLabel = new Label("Image:");
        Button uploadButton = new Button("Upload Image");
        ImageView imageView = new ImageView();
        imageView.setFitWidth(100);  // Set image view size
        imageView.setFitHeight(100);

        uploadButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(new Stage());
            if (file != null) {
                imageView.setImage(new Image(file.toURI().toString()));
            }
        });

        Button saveButton = new Button("Save Entry");
        saveButton.setOnAction(e -> {
        	String title = titleField.getText();
            String content = contentArea.getText();
            Image image = imageView.getImage();
            String imagePath = (image != null) ? image.getUrl() : null; // Use the image URL path as a string

            if (title != null && !title.isEmpty() && content != null && !content.isEmpty()) {
                JournalEntry entry = new JournalEntry();
                entry.setTitle(title);
                entry.setContent(content);
                entry.setImagePath(imagePath); // Store the image path

                // Save the entry to the file
                entry.writeEntryToFile(); // This will append the entry to the file

                // Add the entry to the list of journal entries (optional)
                navigationController.getEntries().add(entry);
                navigationController.showDashboard();
            }
        });
        grid.add(titleLabel, 0, 0);
        grid.add(titleField, 1, 0);
        grid.add(contentLabel, 0, 1);
        grid.add(contentArea, 1, 1);
        grid.add(imageLabel, 0, 2);
        grid.add(uploadButton, 1, 2);
        grid.add(imageView, 1, 3);
        grid.add(saveButton, 1, 4);

        return new Scene(grid, 500, 400);
    }
}
