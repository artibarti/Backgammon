import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import utils.Game;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception
    {

        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane root = fxmlLoader.load(getClass().getResource("/view/main.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("/resources/styles/styles.css").toExternalForm());

        primaryStage.setTitle("Backgammon");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
