package controller;

import javafx.stage.Stage;
import model.JournalEntry;
import util.JournalFileManager;
import view.DashboardView;
import view.JournalEntryView;
import javafx.collections.ObservableList;
import view.LoginView;
import javafx.collections.FXCollections;

public class NavigationController {
    private Stage stage;
    private ObservableList<JournalEntry> entries;
    private DashboardView dashboardView;  // Add reference to DashboardView

    public NavigationController(Stage stage) {
        this.stage = stage;
        this.entries = FXCollections.observableArrayList();  // Initialize the entries list
        this.dashboardView = new DashboardView(this);  // Initialize DashboardView
    }

    public void showLoginView() {
        LoginView loginView = new LoginView(this);
        stage.setScene(loginView.getLoginScene());
        stage.show();
    }

    public void showDashboard() {
        stage.setScene(dashboardView.getDashboardScene());  // Use the existing dashboardView
    }

    public void showJournalEntryView() {
        JournalEntryView journalEntryView = new JournalEntryView(this);
        stage.setScene(journalEntryView.getJournalEntryScene());
    }

    public ObservableList<JournalEntry> getEntries() {
        return entries;
    }

    public void addJournalEntry(JournalEntry journalEntry) {
        entries.add(journalEntry);  // Add the new entry to the list
        JournalFileManager.saveEntriesToFile(entries); // Save to file after adding
        showDashboard();
    }

    public void setEntriesList(ObservableList<JournalEntry> entries) {
        this.entries = entries;
    }

}
