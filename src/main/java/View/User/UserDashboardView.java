package View.User;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class UserDashboardView {

    private final BorderPane root;
    private ImageView maskot1, maskot2;

    public UserDashboardView() {
        root = new BorderPane();
        root.getStyleClass().add("user-dashboard-root");

        root.setTop(createTopBar());
        root.setLeft(createFilterSidebar());

        // Placeholder untuk konten utama (daftar kos)
        Label contentPlaceholder = new Label("Daftar Kos Akan Tampil di Sini");
        contentPlaceholder.getStyleClass().add("content-placeholder");
        root.setCenter(contentPlaceholder);
    }

    private Node createTopBar() {
        HBox topBar = new HBox(30);
        topBar.setPadding(new Insets(15, 30, 15, 30));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.getStyleClass().add("top-bar");

        // Logo
        HBox titleSection = new HBox(0);
        titleSection.setAlignment(Pos.CENTER);
        Image bapaKosIconImg = new Image(getClass().getResourceAsStream("/img/bapa-kos-icon-png.png"));
        maskot1 = new ImageView(bapaKosIconImg);
        maskot1.setFitHeight(60);
        maskot1.setFitWidth(60);
        Image bapaKosTextImg = new Image(getClass().getResourceAsStream("/img/bapa-kos-text-png.png"));
        maskot2 = new ImageView(bapaKosTextImg);
        maskot2.setFitHeight(60);
        maskot2.setFitWidth(110);
        titleSection.getChildren().addAll(maskot1, maskot2);

        // Spacer
        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Search Bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search Anything...");
        searchField.getStyleClass().add("search-field");

        // Profile Button
        Button profileButton = new Button("John Doe");
        profileButton.getStyleClass().add("profile-button");

        topBar.getChildren().addAll(titleSection, spacer, searchField, profileButton);
        return topBar;
    }

    private Node createFilterSidebar() {
        VBox filterSidebar = new VBox(20);
        filterSidebar.setPadding(new Insets(25));
        filterSidebar.getStyleClass().add("filter-sidebar");
        filterSidebar.setPrefWidth(300);

        Label title = new Label("Custom Filter");
        title.getStyleClass().add("filter-title");

        // Placeholder untuk komponen filter
        filterSidebar.getChildren().addAll(
                title,
                new Label("Filter Lokasi..."),
                new Label("Filter Harga..."),
                new Label("Filter Tipe Kos...")
        );

        return filterSidebar;
    }

    public Parent getView() {
        return root;
    }
}