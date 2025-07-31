package View.Admin;

import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public class HapusKosMenuView {
    public Parent getView() {
        Label label = new Label("Ini adalah Halaman Hapus Kos");
        label.setFont(new Font("Arial", 24));
        StackPane layout = new StackPane(label);
        layout.setAlignment(Pos.CENTER);
        return layout;
    }
}
