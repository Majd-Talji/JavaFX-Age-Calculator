
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Majd Talji <en.majd.talji@gmail.com>
 */
public class Main extends Application {

    static ComboBoxStyle comboBoxStyle = new ComboBoxStyle();
    static DatePickerStyle datePickerStyle = new DatePickerStyle();

    @Override
    public void start(Stage stage) throws Exception {
        MenuBar menuBar = new MenuBar();

        Menu menu = new Menu("View");

        RadioMenuItem styleComboBox = new RadioMenuItem("Style 1");
        RadioMenuItem styleDatePickerBox = new RadioMenuItem("Style 2");

        ToggleGroup group = new ToggleGroup();
        styleComboBox.setToggleGroup(group);
        styleDatePickerBox.setToggleGroup(group);

        styleComboBox.setSelected(true);

        menuBar.getMenus().add(menu);
        menu.getItems().addAll(styleComboBox, styleDatePickerBox);

        VBox root = new VBox();
        root.getChildren().add(menuBar);

        SubScene subScene = new SubScene(comboBoxStyle, 430, 420);
        root.getChildren().add(subScene);

        VBox.setMargin(subScene, new Insets(35, 0, 0, 0));

        Scene scene = new Scene(root);
        stage.setTitle("Age Calculator");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);

        styleComboBox.setOnAction((event) -> {
            subScene.setRoot(comboBoxStyle);
        });

        styleDatePickerBox.setOnAction((event) -> {
            subScene.setRoot(datePickerStyle);
        });

    }

    public static void main(String[] args) {
        launch();
    }

}
