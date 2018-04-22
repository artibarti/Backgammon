

package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuController
{

    @FXML
    private Button btnStartNew;

    @FXML
    private Button btnOptions;

    @FXML
    private Button btnRankings;

    @FXML
    private Button btnHelp;

    @FXML
    private Button btnExit;

    private MainController mainController;

    public MenuController() {}

    @FXML
    private void initialize()
    {
        btnStartNew.setOnAction(this::btnStartNewClicked);
        btnOptions.setOnAction(this::btnOptionsClicked);
        btnRankings.setOnAction(this::btnRankingsClicked);
        btnHelp.setOnAction(this::btnHelpClicked);
        btnExit.setOnAction(this::btnExitClicked);
    }

    private void btnStartNewClicked(ActionEvent event)
    {
        mainController.showBoard();
    }

    private void btnOptionsClicked(ActionEvent event)
    {

    }

    private void btnRankingsClicked(ActionEvent event)
    {

    }

    private void btnHelpClicked(ActionEvent event)
    {
        mainController.showHelp();
    }

    private void btnExitClicked(ActionEvent event)
    {

    }

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }
}
