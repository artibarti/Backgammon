package com.artibarti.backgammon.controller;

import com.artibarti.backgammon.model.Board;
import com.artibarti.backgammon.model.Turn;
import com.artibarti.backgammon.service.XMLHandler;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MainController
{

    @FXML
    AnchorPane apMain;

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    MenuController menuController;
    BoardController boardController;
    HelpController helpController;
    ControlsController controlsController;

    @FXML
    private void initialize()
    {
        logger.info("enter initialize");
        showMenu();
    }

    public void showMenu()
    {
        logger.info("enter showMenu");

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
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void showHelp()
    {
        logger.info("enter showHelp");

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
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public void showControls()
    {
        logger.info("enter showControls");

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/view/controls.fxml"));
            AnchorPane apHelp = (AnchorPane) fxmlLoader.load();
            apMain.getChildren().clear();
            apMain.getChildren().add(apHelp);
            controlsController = fxmlLoader.getController();
            controlsController.setMainController(this);
        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public void startGame()
    {
        logger.info("enter startGame");

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
            boardController.start();

        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public void continueGame()
    {
        logger.info("enter continueGame");

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

            Pair<Board, Turn> gameData = XMLHandler.readXML();
            boardController.resume(gameData.getKey(), gameData.getValue());
        }
        catch (IOException e)
        {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

    }

    public void exit()
    {
        logger.info("enter exit");
        logger.info("exit application...");
        Platform.exit();
    }
}
