module FinalProjectAPD {
    requires javafx.controls; // For JavaFX UI controls
    requires javafx.fxml;
	requires javafx.graphics;     // If you're using FXML files (optional)
    
    exports model;            // Export the package containing your Main class
}
