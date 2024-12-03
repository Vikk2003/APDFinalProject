package model;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class JournalEntry {
    private StringProperty title;
    private StringProperty content;
    private String imagePath;  // Store image path
    private LocalDate entryDate;

    private static final String FILE_NAME = "journal-entries.txt"; // File to store journal entries

    public JournalEntry() {
        this.title = new SimpleStringProperty();
        this.content = new SimpleStringProperty();
        this.imagePath = null;  // Initially, no image
        this.entryDate = LocalDate.now();
    }

    // Getters and setters for title, content, image path, and entry date
    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getContent() {
        return content.get();
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getEntryDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return entryDate.format(formatter);
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    @SuppressWarnings("exports")
	public StringProperty titleProperty() {
        return title;
    }

    @SuppressWarnings("exports")
	public StringProperty contentProperty() {
        return content;
    }

    // Method to write journal entry to a file
    public void writeEntryToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            String entry = String.format("Title: %s\nDate: %s\nImage: %s\nContent: %s\n\n",
                    title.get(), entryDate.toString(), imagePath != null ? imagePath : "No image", content.get());
            bw.write(entry);
            System.out.println("Journal entry saved to file: " + entry);
        } catch (IOException e) {
            System.err.println("Error writing journal entry to file: " + e.getMessage());
        }
    }
}
