package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ControlsController
{
    @FXML
    Button btnBack;

    private MainController mainController;

    @FXML
    private void initialize()
    {
        btnBack.setOnAction(this::btnBackClicked);
    }

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    private void btnBackClicked(ActionEvent event)
    {
        mainController.showMenu();
    }

}
