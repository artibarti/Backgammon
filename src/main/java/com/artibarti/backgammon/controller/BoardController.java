
package com.artibarti.backgammon.controller;

import com.artibarti.backgammon.service.XMLHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import com.artibarti.backgammon.model.Board;
import com.artibarti.backgammon.utils.DesignManager;
import com.artibarti.backgammon.utils.GameUtil;
import com.artibarti.backgammon.model.Turn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.log;
import static java.lang.Math.min;



public class BoardController
{
    @FXML
    private VBox field1, field2, field3, field4, field5, field6, field7, field8, field9, field10, field11, field12, field13, field14, field15, field16, field17, field18, field19, field20, field21, field22, field23, field24;

    @FXML
    private AnchorPane apBoard;

    @FXML
    private Pane dice1, dice2, dice3, dice4;

    @FXML
    private HBox hbplayer1BorneCheckers, hbplayer2BorneCheckers, hbplayer1KickedCheckers, hbplayer2KickedCheckers;

    @FXML
    Label player1KickedCheckersCount, player2KickedCheckersCount, player1BorneCheckersCount, player2BorneCheckersCount;

    @FXML
    Button btnBack;

    private static Logger logger = LoggerFactory.getLogger(BoardController.class);

    private MainController mainController;

    private Board board;
    private List<Pane> fields;
    private List<Pane> dices;
    private Turn currentTurn;
    private int selectedFieldID;
    private DesignManager designManager;

    public void setMainController(MainController mainController)
    {
        this.mainController = mainController;
    }

    @FXML
    private void initialize()
    {
        logger.info("enter initialize");

        btnBack.setOnAction(this::backToMenu);

        fields = new ArrayList<>();
        dices = new ArrayList<>();

        dices.add(dice1);
        dices.add(dice2);
        dices.add(dice3);
        dices.add(dice4);

        designManager = new DesignManager();
        board = new Board();

    }

    public void resume(Board board, Turn turn)
    {
        logger.info("enter resume");

        this.board = board;
        this.currentTurn = turn;

        initBases();

        currentTurn.setChoosableFields(GameUtil.getFieldsCanStepFrom(board, currentTurn.getPlayer(), currentTurn.getDiceNumbers()));

        if (currentTurn.getChoosableFields().size() != 0)
        {
            selectedFieldID = currentTurn.getChoosableFields().get(0);
            designManager.setStyle("selection", fields.get(selectedFieldID), getHighlighForField(selectedFieldID));
            refresh();
        }
        else
        {
            nextTurn();
        }


    }

    public void start()
    {
        logger.info("enter start");

        GameUtil.initBoard(board);
        nextTurn();
    }

    public void nextTurn()
    {
        logger.info("enter nextTurn");

        if (currentTurn == null)
        {
            currentTurn = new Turn(GameUtil.Player1ID);
            initBases();
            refresh();
        }
        else if (currentTurn.getPlayer() == GameUtil.Player1ID)
        {
            currentTurn = new Turn(GameUtil.Player2ID);
            initBases();
        }
        else if (currentTurn.getPlayer() == GameUtil.Player2ID)
        {
            currentTurn = new Turn(GameUtil.Player1ID);
            initBases();
        }


        currentTurn.setChoosableFields(GameUtil.getFieldsCanStepFrom(board, currentTurn.getPlayer(), currentTurn.getDiceNumbers()));

        if (currentTurn.getChoosableFields().size() != 0)
        {
            selectedFieldID = currentTurn.getChoosableFields().get(0);
            designManager.setStyle("selection", fields.get(selectedFieldID), getHighlighForField(selectedFieldID));
        }
        else
        {
            nextTurn();
        }
    }


    public void initBases()
    {
        logger.info("enter initBases");

        fields.clear();

        if (currentTurn.getPlayer() == GameUtil.Player1ID)
            fields.add(hbplayer1KickedCheckers);
        if (currentTurn.getPlayer() == GameUtil.Player2ID)
            fields.add(hbplayer2BorneCheckers);

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

        if (currentTurn.getPlayer() == GameUtil.Player1ID)
            fields.add(hbplayer1BorneCheckers);
        if (currentTurn.getPlayer() == GameUtil.Player2ID)
            fields.add(hbplayer2KickedCheckers);

    }

    public void setKeyboardEventHandler()
    {
        logger.info("enter setKeyboardEventHandler");

        apBoard.getScene().addEventFilter(KeyEvent.KEY_PRESSED, keyEvent ->
        {

            logger.info("enter KeyEventHandler");

            getNextSelectedFieldID(keyEvent.getCode());

            designManager.setStyle("selection", fields.get(selectedFieldID), getHighlighForField(selectedFieldID));

            if (keyEvent.getCode() == KeyCode.ENTER)
            {
                if (currentTurn.getMode() == Turn.Mode.SELECT_FROM)
                {
                    if (currentTurn.getChoosableFields().stream().anyMatch(p -> p == selectedFieldID))
                    {
                        currentTurn.setFrom(selectedFieldID);
                        currentTurn.setMode(Turn.Mode.SELECT_TO);
                        currentTurn.setChoosableFields(GameUtil.getFieldsCanStepTo(board, currentTurn.getPlayer(), selectedFieldID, currentTurn.getDiceNumbers()));
                        designManager.setStyle("from", fields.get(selectedFieldID), getHighlighForField(selectedFieldID));
                    }

                }

                else if (currentTurn.getMode() == Turn.Mode.SELECT_TO)
                {
                    if (selectedFieldID == currentTurn.getFrom())
                    {
                        currentTurn.setMode(Turn.Mode.SELECT_FROM);
                        designManager.dropStyle("from");
                        currentTurn.setChoosableFields(GameUtil.getFieldsCanStepFrom(board, currentTurn.getPlayer(), currentTurn.getDiceNumbers()));
                    }

                    else if (currentTurn.getChoosableFields().stream().anyMatch(p -> p == selectedFieldID))
                    {
                        if (GameUtil.step(board, currentTurn.getFrom(), selectedFieldID, currentTurn.getPlayer()) == GameUtil.WINNER_STEP)
                            endGame();

                        if (selectedFieldID == 0 || selectedFieldID == 25)
                            currentTurn.removeStep(abs(currentTurn.getFrom() - selectedFieldID), true);
                        else
                            currentTurn.removeStep(abs(currentTurn.getFrom() - selectedFieldID), false);

                        designManager.dropStyle("from");
                        if (currentTurn.getStepsLeft() == 0)
                        {
                            nextTurn();
                        }
                        else
                        {
                            currentTurn.setMode(Turn.Mode.SELECT_FROM);
                            currentTurn.setChoosableFields(GameUtil.getFieldsCanStepFrom(board, currentTurn.getPlayer(), currentTurn.getDiceNumbers()));

                            if (currentTurn.getChoosableFields().size() != 0)
                            {
                                selectedFieldID = currentTurn.getChoosableFields().get(0);
                                designManager.setStyle("selection", fields.get(selectedFieldID), getHighlighForField(selectedFieldID));
                            }
                            else
                            {
                                nextTurn();
                            }
                        }
                    }

                    refresh();
                }
            }
        });
    }

    public void refresh()
    {
        logger.info("enter refresh");
        drawBoard();
        refreshDices();
    }


    public void getNextSelectedFieldID(KeyCode keyCode)
    {
        logger.info("enter getNextSelectedFieldID");

        if (keyCode == KeyCode.UP || keyCode == KeyCode.W)
        {
            selectedFieldID = 25 - selectedFieldID;
        }
        if (keyCode == KeyCode.DOWN || keyCode == KeyCode.S)
        {
            selectedFieldID = 25 - selectedFieldID;
        }
        if (keyCode == KeyCode.RIGHT || keyCode == KeyCode.D)
        {
            if (selectedFieldID < 12)
            {
                selectedFieldID = selectedFieldID + 1;
            }
            if (selectedFieldID > 13)
            {
                selectedFieldID = selectedFieldID - 1;
            }
        }
        if (keyCode == KeyCode.LEFT || keyCode == KeyCode.A)
        {
            if (selectedFieldID > 0 && selectedFieldID <= 12)
            {
                selectedFieldID = selectedFieldID - 1;
            }
            if (selectedFieldID < 25 && selectedFieldID >= 13)
            {
                selectedFieldID = selectedFieldID + 1;
            }
        }
    }

    public void drawBoard()
    {
        logger.info("enter drawBoard");

        fields.stream()
                .filter(p -> !p.equals(hbplayer1BorneCheckers) && !p.equals(hbplayer2BorneCheckers)
                        && !p.equals(hbplayer1KickedCheckers) && !p.equals(hbplayer2KickedCheckers))
                .forEach(p -> p.getChildren().clear());

        board.getFields().stream()
                .forEach(p -> addCheckers(p.getId(), p.getTeam(), p.getNumberOfCheckers()));

        player1BorneCheckersCount.setText(Integer.toString(board.getBorneCheckers(GameUtil.Player1ID)));
        player2BorneCheckersCount.setText(Integer.toString(board.getBorneCheckers(GameUtil.Player2ID)));
        player1KickedCheckersCount.setText(Integer.toString(board.getKickedCheckers(GameUtil.Player1ID)));
        player2KickedCheckersCount.setText(Integer.toString(board.getKickedCheckers(GameUtil.Player2ID)));

    }

    void addCheckers(int fieldID, int team, int count)
    {
        logger.info("enter addCheckers");

        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader();

            for (int i = 0; i < count; i++)
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
            logger.error(e.getMessage());
        }
    }


    public void refreshDices()
    {
        logger.info("enter refreshDices");

        dices.stream().forEach(d -> d.getStyleClass().clear());

        for (int i = 0; i < currentTurn.getDiceNumbers().size(); i++)
        {
            dices.get(i).getStyleClass().add("dice" + Integer.toString(currentTurn.getDiceNumbers().get(i))
                    + "_" + getColorForDices());
        }
    }

    public String getColorForDices()
    {
        logger.info("enter getColorForDices");

        String result = "";

        if (currentTurn.getPlayer() == GameUtil.Player1ID)
            result = "white";
        if (currentTurn.getPlayer() == GameUtil.Player2ID)
            result = "black";

        return result;
    }

    public String getHighlighForField(int fieldID)
    {
        logger.info("enter getHighlighForField");

        String result = "";

        if (fieldID == 0 || fieldID == 25)
        {
            result = "selection_for_kicked_and_borne_fields";
        }
        if (fieldID >= 1 && fieldID <= 12)
        {
            result = "selection_for_upper_fields";
        }
        if (fieldID >= 13 && fieldID <= 24)
        {
            result = "selection_for_lower_fields";
        }

        return result;
    }


    public void endGame()
    {
        logger.info("enter endGame");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("The match is over!");
        alert.setHeaderText(null);
        alert.setContentText("Player" + (currentTurn.getPlayer() + 1) + " is the winner!");

        alert.showAndWait();
        mainController.showMenu();

    }

    public void backToMenu(ActionEvent event)
    {
        XMLHandler.writeXML(board, currentTurn);
        logger.info("enter backToMenu");
        mainController.showMenu();
    }
}