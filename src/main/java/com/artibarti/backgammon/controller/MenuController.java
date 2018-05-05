

package com.artibarti.backgammon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuController
{

    @FXML
    private Button btnStartNew;

    @FXML
    private Button btnControls;

    @FXML
    private Button btnHelp;

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
        btnControls.setOnAction(this::btnControlsClicked);
        btnHelp.setOnAction(this::btnHelpClicked);
        btnExit.setOnAction(this::btnExitClicked);
    }

    private void btnStartNewClicked(ActionEvent event)
    {
        logger.info("enter btnStartNewClicked");
        mainController.showBoard();
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
