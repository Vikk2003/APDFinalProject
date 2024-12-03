package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.JournalEntry;
import util.JournalFileManager;
import controller.NavigationController;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import util.JournalFileManager;
import javafx.beans.property.SimpleStringProperty;


@SuppressWarnings("unused")
public class DashboardView {
    private NavigationController navigationController;
    private ObservableList<JournalEntry> entries;

    public DashboardView(NavigationController navigationController) {
        this.navigationController = navigationController;
        this.entries = FXCollections.observableArrayList();
    }

    @SuppressWarnings("unchecked")
	public Scene getDashboardScene() {
        BorderPane root = new BorderPane();

        // Table for journal entries
        TableView<JournalEntry> tableView = new TableView<>(entries);
        TableColumn<JournalEntry, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(data -> data.getValue().titleProperty());
        
        // Add new column for Image Path
        TableColumn<JournalEntry, String> imageCol = new TableColumn<>("Image");
        imageCol.setCellValueFactory(data -> {
            String imagePath = data.getValue().getImagePath();
            return imagePath == null || imagePath.isEmpty() ? new SimpleStringProperty("No Image") : new SimpleStringProperty("Image Uploaded");
        });

        tableView.getColumns().addAll(titleCol, imageCol);

        // Buttons for CRUD operations
        Button addButton = new Button("Add");
        Button updateButton = new Button("Update");
        Button deleteButton = new Button("Delete");
        Button exitButton = new Button("Exit");

        HBox buttons = new HBox(10, addButton, updateButton, deleteButton, exitButton);
        buttons.setPadding(new Insets(10));
        buttons.setAlignment(Pos.CENTER);

        exitButton.setOnAction(e -> System.exit(0));

        addButton.setOnAction(e -> navigationController.showJournalEntryView());

        deleteButton.setOnAction(e -> {
            JournalEntry selected = tableView.getSelectionModel().getSelectedItem();
            if (selected != null) {
                entries.remove(selected);
                JournalFileManager.saveEntriesToFile(entries);
            }
        });

        // Ensure the table gets updated when new entries are added
        navigationController.setEntriesList(entries); // Update entries list reference

        root.setCenter(tableView);
        root.setBottom(buttons);

        return new Scene(root, 600, 400);
    }

    public ObservableList<JournalEntry> getEntries() {
        return entries;
    }

    public void addJournalEntry(JournalEntry journalEntry) {
        entries.add(journalEntry);  // Add the new entry to the list
    }
}
