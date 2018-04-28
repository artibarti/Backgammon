
package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Board;
import model.Player;
import utils.GameUtil;
import model.Turn;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;

public class BoardController
{
    @FXML
    private VBox field1, field2, field3, field4, field5, field6, field7, field8, field9, field10, field11, field12, field13, field14, field15, field16, field17, field18, field19, field20, field21, field22, field23, field24;

    @FXML
    private AnchorPane apBoard;

    @FXML
    private Pane dice1, dice2, dice3, dice4;

    @FXML
    private HBox hbRightPlayer1, hbRightPlayer2;

    @FXML
    private VBox dicePanel;

    private MainController mainController;

    private Board board;
    private Player player1;
    private Player player2;
    private List<VBox> fields;
    private List<Pane> dices;
    private Turn currentTurn;
    private int selectedFieldID;
    private Map<Integer, String> highlightsForFields;

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

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

        dices = new ArrayList<>();

        dices.add(dice1);
        dices.add(dice2);
        dices.add(dice3);
        dices.add(dice4);

        highlightsForFields = new HashMap<>();

        highlightsForFields.put(0, "selectedField_upperFields");
        highlightsForFields.put(1, "selectedField_upperFields");
        highlightsForFields.put(2, "selectedField_upperFields");
        highlightsForFields.put(3, "selectedField_upperFields");
        highlightsForFields.put(4, "selectedField_upperFields");
        highlightsForFields.put(5, "selectedField_upperFields");
        highlightsForFields.put(6, "selectedField_upperFields");
        highlightsForFields.put(7, "selectedField_upperFields");
        highlightsForFields.put(8, "selectedField_upperFields");
        highlightsForFields.put(9, "selectedField_upperFields");
        highlightsForFields.put(10, "selectedField_upperFields");
        highlightsForFields.put(11, "selectedField_upperFields");
        highlightsForFields.put(12, "selectedField_lowerFields");
        highlightsForFields.put(13, "selectedField_lowerFields");
        highlightsForFields.put(14, "selectedField_lowerFields");
        highlightsForFields.put(15, "selectedField_lowerFields");
        highlightsForFields.put(16, "selectedField_lowerFields");
        highlightsForFields.put(17, "selectedField_lowerFields");
        highlightsForFields.put(18, "selectedField_lowerFields");
        highlightsForFields.put(19, "selectedField_lowerFields");
        highlightsForFields.put(20, "selectedField_lowerFields");
        highlightsForFields.put(21, "selectedField_lowerFields");
        highlightsForFields.put(22, "selectedField_lowerFields");
        highlightsForFields.put(23, "selectedField_lowerFields");

        player1 = new Player(0);
        player2 = new Player(1);
        board = new Board();

        GameUtil.initBoard(board, player1, player2);
        nextTurn();
    }

    public void nextTurn()
    {

        if (currentTurn == null)
        {
            currentTurn = new Turn(player1);
            selectedFieldID = 0;
        }
        else if (currentTurn.getPlayer().getTeam() == player1.getTeam())
        {
            currentTurn = new Turn(player2);
            selectedFieldID = 0;
        }
        else if (currentTurn.getPlayer().getTeam() == player2.getTeam())
        {
            currentTurn = new Turn(player1);
            selectedFieldID = 23;
        }

        refresh(true, 0);
    }

    public void nextStep()
    {
        if (currentTurn.getStepsLeft() == 0)
        {
            nextTurn();
        }
        else
        {
            currentTurn.nextStep();
        }
    }

    public void setKeyboardEventHandler()
    {
        apBoard.getScene().addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>()
        {
            public void handle(KeyEvent keyEvent)
            {
                int oldSelectedFieldID = selectedFieldID;
                boolean shouldRefreshEverything = false;

                if (keyEvent.getCode() == KeyCode.UP)
                {
                    selectedFieldID = 23 - selectedFieldID;
                }
                if (keyEvent.getCode() == KeyCode.DOWN)
                {
                    selectedFieldID = 23 - selectedFieldID;
                }
                if (keyEvent.getCode() == KeyCode.RIGHT)
                {
                    if (selectedFieldID < 11)
                    {
                        selectedFieldID = selectedFieldID + 1;
                    }
                    if (selectedFieldID > 12)
                    {
                        selectedFieldID = selectedFieldID - 1;
                    }
                }
                if (keyEvent.getCode() == KeyCode.LEFT)
                {
                    if (selectedFieldID > 0 && selectedFieldID <= 11)
                    {
                        selectedFieldID = selectedFieldID - 1;
                    }
                    if (selectedFieldID < 23 && selectedFieldID >= 12)
                    {
                        selectedFieldID = selectedFieldID + 1;
                    }
                }
                if (keyEvent.getCode() == KeyCode.ENTER)
                {

                    if (currentTurn.getFrom() == -1 && GameUtil.getFieldsCanStepFrom(board, currentTurn.getPlayer(),
                            currentTurn.getDiceNumbers()).stream().anyMatch(p -> p == selectedFieldID))
                    {
                        currentTurn.setFrom(selectedFieldID);
                    }

                    else if (currentTurn.getFrom() != -1)
                    {
                        if (GameUtil.getFieldsCanStepTo(board, currentTurn.getPlayer(), currentTurn.getFrom(),
                                currentTurn.getDiceNumbers()).stream().anyMatch(p -> p == selectedFieldID))
                        {
                            GameUtil.step(board, currentTurn.getFrom(), selectedFieldID, currentTurn.getPlayer());
                            currentTurn.removeStep(abs(currentTurn.getFrom() - selectedFieldID));
                            currentTurn.nextStep();
                            nextStep();
                            shouldRefreshEverything = true;
                        }
                    }
                }

                refresh(shouldRefreshEverything, oldSelectedFieldID);

            }
        });
    }

    public void refresh(boolean shouldRefreshEerything, int oldSelectedFieldID)
    {

        fields.get(oldSelectedFieldID).getStyleClass().clear();
        fields.get(selectedFieldID).getStyleClass().add(highlightsForFields.get(selectedFieldID));

        if (currentTurn.getFrom() != -1)
        {
            fields.get(currentTurn.getFrom()).getStyleClass().clear();
            fields.get(currentTurn.getFrom()).getStyleClass().add("selectedField");
        }

        if (!shouldRefreshEerything)
        {
            return;
        }

        drawBoard();

        dices.stream().forEach(d -> d.getStyleClass().clear());

        for (int i = 0; i<currentTurn.getDiceNumbers().size(); i++)
        {
            dices.get(i).getStyleClass().add("dice" + Integer.toString(currentTurn.getDiceNumbers().get(i))
                    + "_" + GameUtil.getColorForDices(currentTurn.getPlayer()));
        }
    }

    public void drawBoard()
    {
        fields.stream().forEach(p -> p.getChildren().clear());
        board.getFields().stream()
                .forEach(p -> {addCheckers(p.getId(), p.getTeam(), p.getNumberOfCheckers());});

    }

    void addCheckers(int fieldID, int team, int count)
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();

            for (int i = 0; i<count; i++)
            {
                AnchorPane apChecker = fxmlLoader.load(getClass().getResource("/view/checker.fxml"));
                apChecker.getStylesheets().clear();

                fields.get(fieldID).getChildren().add(apChecker);
                if (team == 0)
                    fields.get(fieldID).getChildren().get(fields.get(fieldID).getChildren().size() - 1).getStyleClass().add("checker_white");
                if (team == 1)
                    fields.get(fieldID).getChildren().get(fields.get(fieldID).getChildren().size() - 1).getStyleClass().add("checker_black");
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
