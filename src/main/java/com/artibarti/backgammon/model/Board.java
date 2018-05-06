package com.artibarti.backgammon.model;

import com.artibarti.backgammon.utils.GameUtil;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Representation of a Backgammon board.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Board
{

    /**
     * A list of {@link com.artibarti.backgammon.model.Field} representing the fields of the board.
     * representing the 24 fields of the board.
     */
    @XmlElement(name="field", type=Field.class)
    private List<Field> fields;

    /**
     * An integer value representing the kicked checkers for the first player.
     */
    @XmlAttribute
    private int player1KickedCheckers = 0;

    /**
     * An integer value representing the kicked checkers for the second player.
     */
    @XmlAttribute
    private int player2KickedCheckers = 0;

    /**
     * An integer value representing the borne checkers for the first player.
     */
    @XmlAttribute
    private int player1BorneCheckers = 0;

    /**
     * An integer value representing the borne checkers for the second player.
     */
    @XmlAttribute
    private int player2BorneCheckers = 0;

    /**
     * Constructor.
     */
    public Board()
    {

        fields = new ArrayList<>();

        for (int i = 1; i<25; i++)
        {
            Field field = new Field(i);
            fields.add(field);
        }
    }

    /**
     * Getter method for fields.
     *
     * @return A list of {@link com.artibarti.backgammon.model.Field} of the fields of the board.
     */
    public List<Field> getFields()
    {
        return fields;
    }

    /**
     * Replace the fields of the board with a new {@link List} of {@link Field} objects.
     *
     * @param fields The new list of {@link com.artibarti.backgammon.model.Field} .
     */
    public void setFields(List<Field> fields)
    {
        this.fields = fields;
    }

    /**
     * Getter method for kicked checkers.
     *
     * @param player The player to return the kicked checkers for.
     *
     * @return The number of kicked checkers for the player.
     */
    public int getKickedCheckers(int player)
    {
        if (player == GameUtil.Player1ID)
            return player1KickedCheckers;

        if (player == GameUtil.Player2ID)
            return player2KickedCheckers;

        return 0;
    }

    /**
     * Getter method for borne checkers.
     *
     * @param player The player to return the borne checkers for.
     *
     * @return The number of borne checkers for player.
     */
    public int getBorneCheckers(int player)
    {
        if (player == GameUtil.Player1ID)
            return player1BorneCheckers;

        if (player == GameUtil.Player2ID)
            return player2BorneCheckers;

        return 0;
    }

    /**
     * Method to add one kicked checker for player.
     *
     * @param player The player to add one kicked checker for.
     */
    public void addKickedChecker(int player)
    {
        if (player == GameUtil.Player1ID)
            this.player1KickedCheckers += 1;

        if (player == GameUtil.Player2ID)
            this.player2KickedCheckers += 1;
    }

    /**
     * Method to add one borne checker for player.
     *
     * @param player The player to add one borne checker for.
     */
    public void addBorneChecker(int player)
    {
        if (player == GameUtil.Player1ID)
            this.player1BorneCheckers += 1;

        if (player == GameUtil.Player2ID)
            this.player2BorneCheckers += 1;
    }

    /**
     * Method to take one kicked checker for player.
     *
     * @param player The player to take one kicked checker from.
     */
    public void minusKickedChecker(int player)
    {
        if (player == GameUtil.Player1ID && player1KickedCheckers > 0)
            this.player1KickedCheckers -= 1;

        if (player == GameUtil.Player2ID && player2KickedCheckers > 0)
            this.player2KickedCheckers -= 1;
    }

    /**
     * Method to add one checker to field.
     *
     * @param fieldID The id of the field.
     *
     * @param player The player owns the checker.
     */
    public void addChecker(int fieldID, int player)
    {
        fields.stream()
                .filter(p -> p.getId() == fieldID)
                .forEach(p -> p.addCheckers(player, 1));
    }

    /**
     * Method to delete checkers from a field.
     *
     * @param fieldID The id of the field.
     *
     * @param count The number of checkers.
     */
    public void deleteChecker(int fieldID, int count)
    {
        fields.stream()
                .filter(p -> p.getId() == fieldID)
                .forEach(p -> p.deleteChecker(count));
    }

    /**
     * Method to delete all checkers from a field.
     *
     * @param fieldID The id of the field.
     */
    public void deleteCheckers(int fieldID)
    {
        fields.stream()
                .filter(p -> p.getId() == fieldID)
                .forEach(p -> p.deleteCheckers());
    }

    /**
     * Method to get a {@link com.artibarti.backgammon.model.Field}.
     *
     * @param fieldID The id of the field.
     *
     * @return The first {@link Field} found with the given ID.
     */
    public Field getField(int fieldID)
    {
        return fields.stream()
                .filter(p -> p.getId() == fieldID)
                .findFirst()
                .get();
    }
}
