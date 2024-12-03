package util;

import model.JournalEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JournalFileManager {

    // Load entries from the file
    public static ObservableList<JournalEntry> loadEntriesFromFile() {
        ObservableList<JournalEntry> entries = FXCollections.observableArrayList();

        try {
            File file = new File("journal_entries.txt");

            // If file doesn't exist, create it
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            JournalEntry currentEntry = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Title:")) {
                    if (currentEntry != null) {
                        entries.add(currentEntry);  // Add previous entry
                    }
                    currentEntry = new JournalEntry();
                    currentEntry.setTitle(line.substring(7).trim());
                } else if (line.startsWith("Content:")) {
                    if (currentEntry != null) {
                        currentEntry.setContent(line.substring(9).trim());
                    }
                } else if (line.startsWith("Image:")) {
                    if (currentEntry != null) {
                        currentEntry.setImagePath(line.substring(7).trim());
                    }
                }
            }

            if (currentEntry != null) {
                entries.add(currentEntry); // Add the last entry
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entries;
    }

    // Save entries to the file
    public static void saveEntriesToFile(ObservableList<JournalEntry> entries) {
        try {
            File file = new File("journal_entries.txt");

            // If the file doesn't exist, create it
            if (!file.exists()) {
                file.createNewFile();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (JournalEntry entry : entries) {
                writer.write("Title: " + entry.getTitle());
                writer.newLine();
                writer.write("Content: " + entry.getContent());
                writer.newLine();

                // Save image path if it exists
                if (entry.getImagePath() != null) {
                    writer.write("Image: " + entry.getImagePath());
                    writer.newLine();
                }

                writer.newLine(); // Separate entries with a blank line
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
