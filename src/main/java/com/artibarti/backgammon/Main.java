package com.artibarti.backgammon;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        logger.info("enter start method");

        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane root = fxmlLoader.load(getClass().getResource("/view/main.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(getClass().getResource("/styles/styles.css").toExternalForm());

        primaryStage.setTitle("Backgammon");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        logger.info("enter main");
        launch(args);
    }

}
