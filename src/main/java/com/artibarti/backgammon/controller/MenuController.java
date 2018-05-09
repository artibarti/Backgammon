

package com.artibarti.backgammon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MenuController
{

    @FXML
    private Button btnStartNew;

    @FXML
    private Button btnControls;

    @FXML
    private Button btnHelp;

    @FXML
    private Button btnContinue;

    @FXML
    private Button btnExit;

    private static Logger logger = LoggerFactory.getLogger(HelpController.class);

    private MainController mainController;

    public MenuController()
    {
        logger.info("enter MenuController");
    }

    @FXML
    private void initialize()
    {
        logger.info("enter initialize");
        btnStartNew.setOnAction(this::btnStartNewClicked);
        btnContinue.setOnAction(this::btnContinueClicked);
        btnControls.setOnAction(this::btnControlsClicked);
        btnHelp.setOnAction(this::btnHelpClicked);
        btnExit.setOnAction(this::btnExitClicked);

        Path path_to_board = Paths.get(System.getProperty("user.home") + File.separator + "BackgammonUserData" + File.separator + "board.xml");
        Path path_to_turn = Paths.get(System.getProperty("user.home") + File.separator + "BackgammonUserData" + File.separator + "turn.xml");

        if (Files.exists(path_to_board) && Files.exists(path_to_turn))
        {
            btnContinue.setDisable(false);
        }
        else
        {
            btnContinue.setDisable(true);
        }
    }

    private void btnStartNewClicked(ActionEvent event)
    {
        logger.info("enter btnStartNewClicked");
        mainController.startGame();
    }

    private void btnContinueClicked(ActionEvent event)
    {
        logger.info("enter btnContinueClicked");
        mainController.continueGame();
    }

    private void btnHelpClicked(ActionEvent event)
    {
        logger.info("enter btnHelpClicked");
        mainController.showHelp();
    }

    private void btnControlsClicked(ActionEvent event)
    {
        logger.info("enter btnControlsClicked");
        mainController.showControls();
    }

    private void btnExitClicked(ActionEvent event)
    {
        logger.info("enter btnExitClicked");
        mainController.exit();
    }

    public void setMainController(MainController mainController)
    {
        logger.info("enter setMainController");
        this.mainController = mainController;
    }
}
