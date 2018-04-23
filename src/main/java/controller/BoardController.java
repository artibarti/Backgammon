package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utils.Game;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BoardController
{

    @FXML
    private VBox field1, field2, field3, field4, field5, field6, field7, field8, field9, field10, field11, field12, field13, field14, field15, field16, field17, field18, field19, field20, field21, field22, field23, field24;

    @FXML
    private AnchorPane apBoard;

    private MainController mainController;
    private Game game;
    private List<VBox> fields;

    private int currentField;
    private int currentPlayer;
    private int from;

    @FXML
    private void initialize()
    {
        fields = new ArrayList<>();

        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        fields.add(field4);
        fields.add(field5);
        fields.add(field6);
        fields.add(field7);
        fields.add(field8);
        fields.add(field9);
        fields.add(field10);
        fields.add(field11);
        fields.add(field12);
        fields.add(field13);
        fields.add(field14);
        fields.add(field15);
        fields.add(field16);
        fields.add(field17);
        fields.add(field18);
        fields.add(field19);
        fields.add(field20);
        fields.add(field21);
        fields.add(field22);
        fields.add(field23);
        fields.add(field24);

        game = new Game();
        syncronizeBoard();
        currentField = 0;
        currentPlayer = 0;
        from = -1;
    }

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    public void syncronizeBoard()
    {
        for (int i = 0; i<24; i++)
        {
            fields.get(i).getChildren().clear();
        }

        for (int i = 0; i < 24; i++)
        {
            for (int j = 0; j < game.getBoard().getFields().get(i).getCheckers().size(); j++)
            {
                addChecker(i, game.getBoard().getFields().get(i).getTeam());
            }
        }
    }

    void addChecker(int fieldID, int team)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();
            AnchorPane apChecker = fxmlLoader.load(getClass().getResource("/view/checker.fxml"));
            apChecker.getStylesheets().clear();

            fields.get(fieldID).getChildren().add(apChecker);
            if (team == 0)
                fields.get(fieldID).getChildren().get(fields.get(fieldID).getChildren().size() - 1).getStyleClass().add("checker_white");
            if (team == 1)
                fields.get(fieldID).getChildren().get(fields.get(fieldID).getChildren().size() - 1).getStyleClass().add("checker_black");
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void deleteChecker(int fieldID)
    {
        fields.get(fieldID).getChildren().remove(fields.get(fieldID).getChildren().size() - 1);
    }


    public void setKeyboardEventHandler()
    {
        apBoard.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent keyEvent) {

                int old = currentField;

                if (keyEvent.getCode() == KeyCode.UP)
                {
                    currentField = 23 - currentField;
                    highlightSelectedField(old);
                }
                if (keyEvent.getCode() == KeyCode.DOWN)
                {
                    currentField = 23 - currentField;
                    highlightSelectedField(old);
                }
                if (keyEvent.getCode() == KeyCode.RIGHT)
                {
                    if (currentField < 11) currentField = currentField + 1;
                    if (currentField > 12) currentField = currentField - 1;
                    highlightSelectedField(old);
                }
                if (keyEvent.getCode() == KeyCode.LEFT)
                {
                    if (currentField > 0 && currentField <= 11) currentField = currentField - 1;
                    if (currentField < 23 && currentField >= 12) currentField = currentField + 1;
                    highlightSelectedField(old);
                }
                if (keyEvent.getCode() == KeyCode.ENTER)
                {
                    System.out.println("enter hit");
                    if (from == -1)
                    {
                        if (game.canStep(currentField, 22, currentPlayer))
                        {
                            from = currentField;
                        }
                    }
                    else if (from != -1)
                    {
                        if (game.canStep(from, currentField, currentPlayer))
                        {
                            game.step(from, currentField, currentPlayer);
                            syncronizeBoard();
                            from = -1;
                            currentPlayer = 1;
                        }
                        from = -1;
                    }
                }
            }
        });
    }

    public void highlightSelectedField(int old)
    {
        fields.get(old).getStyleClass().clear();
        fields.get(currentField).getStyleClass().add("selectedField");
    }
}
