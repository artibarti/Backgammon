package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController
{

    @FXML
    AnchorPane apMain;

    MenuController menuController;
    BoardController boardController;
    HelpController helpController;

    @FXML
    private void initialize()
    {
        showMenu();
    }

    public void showMenu()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/menu.fxml"));
            AnchorPane apMenu = (AnchorPane) fxmlLoader.load();
            apMain.getChildren().clear();
            apMain.getChildren().add(apMenu);
            menuController = fxmlLoader.getController();
            menuController.setMainController(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showHelp()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/help.fxml"));
            AnchorPane apHelp = (AnchorPane) fxmlLoader.load();
            apMain.getChildren().clear();
            apMain.getChildren().add(apHelp);
            helpController = fxmlLoader.getController();
            helpController.setMainController(this);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void showBoard()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/board.fxml"));
            AnchorPane apBoard = (AnchorPane) fxmlLoader.load();
            boardController = fxmlLoader.getController();
            boardController.setMainController(this);

            apMain.getChildren().clear();
            apMain.getChildren().add(apBoard);
            boardController.setKeyboardEventHandler();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
