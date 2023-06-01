module com.example.bookclubdesktop {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.example.bookclubdesktop to javafx.fxml;
    exports com.example.bookclubdesktop;
}