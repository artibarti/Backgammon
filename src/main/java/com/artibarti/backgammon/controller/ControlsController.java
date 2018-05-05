package com.artibarti.backgammon.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControlsController
{
    @FXML
    Button btnBack;

    private static Logger logger = LoggerFactory.getLogger(ControlsController.class);

    private MainController mainController;

    @FXML
    private void initialize()
    {
        logger.info("enter initialize");
        btnBack.setOnAction(this::btnBackClicked);
        logger.info("exit initialize");
    }

    public void setMainController(MainController mainController)
    {
        logger.info("enter setMainController");
        this.mainController = mainController;
        logger.info("exit setMainController");
    }

    private void btnBackClicked(ActionEvent event)
    {
        logger.info("enter btnBackClicked");
        mainController.showMenu();
        logger.info("exit btnBackClicked");
    }

}
