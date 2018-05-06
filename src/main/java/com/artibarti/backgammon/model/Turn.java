package com.artibarti.backgammon.model;

import com.artibarti.backgammon.utils.GameUtil;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents one turn in the game.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Turn
{

    /**
     * Type for selection mode. The current player can select a field to step from, or a field to step to.
     */
    public enum Mode{ SELECT_FROM, SELECT_TO }

    /**
     * The mode of selection.
     */
    private Mode mode;

    /**
     * The ID of the {@link com.artibarti.backgammon.model.Field} the current player is stepping from.
     * Available only in SELECT_TO mode.
     */
    @XmlAttribute(name="from")
    private int from;

    /**
     * The ID of the current player.
     */
    @XmlAttribute(name="player")
    private int player;

    /**
     * {@link List} of integers representing the dice numbers for the turn.
     */
    @XmlElementWrapper(name="dices")
    @XmlElement(name="dicenumber")
    private List<Integer> diceNumbers;

    /**
     * {@link List} of integers representing the IDs of the {@link com.artibarti.backgammon.model.Field}s
     * the current player can choose.
     */
    @XmlElementWrapper(name="choosableFileds")
    @XmlElement(name="choosableField")
    private List<Integer> choosableFields;

    /**
     * Default contructor.
     */
    public Turn()
    {
        initDiceNumbers();
        mode = Mode.SELECT_FROM;
    }

    /**
     * Constructor.
     * @param player The player of the turn.
     */
    public Turn(int player)
    {
        mode = Mode.SELECT_FROM;
        choosableFields = new ArrayList<>();
        this.player = player;
        initDiceNumbers();
    }

    /**
     * Method to get how many more steps the turn has.
     * @return The number of remaining steps.
     */
    public int getStepsLeft()
    {
        return diceNumbers.size();
    }

    /**
     * Method to get the player for the turn.
     * @return The player of the turn.
     */
    public int getPlayer()
    {
        return player;
    }

    /**
     * Set the value of {@link Turn#from}. Also set the {@link Turn#mode} to SELECT_TO.
     * @param from The new value for {@link Turn#from}.
     */
    public void setFrom(int from)
    {
        this.from = from;
        mode = Mode.SELECT_TO;
    }

    /**
     * Method to get the current value of {@link Turn#from}.
     * @return {@link Turn#from}.
     */
    public int getFrom()
    {
        return from;
    }

    /**
     * Method to get the dice numbers.
     * @return {@link Turn#diceNumbers}.
     */
    public List<Integer> getDiceNumbers()
    {
        return diceNumbers;
    }

    /**
     * Method to remove a value from {@link Turn#diceNumbers}.
     * @param step The length of the step.
     * @param bearingOff Defines whether the player is stepping to its base or not. If the value is true,
     *  the method will remove the first value from {@link Turn#diceNumbers} which is greater then {@code step}
     *  or greater then that. If the value is false, only the first value equal to {@code step} will be removed.
     */
    public void removeStep(int step, boolean bearingOff)
    {
        if (!bearingOff)
        {
            for (int i = 0; i< diceNumbers.size(); i++)
            {
                if (diceNumbers.get(i) == step)
                {
                    diceNumbers.remove(i);
                    return;
                }
            }
        }
        else
        {
            for (int i = 0; i< diceNumbers.size(); i++)
            {
                if (diceNumbers.get(i) >= step)
                {
                    diceNumbers.remove(i);
                    return;
                }
            }
        }
    }

    /**
     * Generate new dice numbers by calling the method {@link GameUtil#generateDiceNumber()}.
     */
    private void initDiceNumbers()
    {
        diceNumbers = new ArrayList<>();
        diceNumbers.add(GameUtil.generateDiceNumber());
        diceNumbers.add(GameUtil.generateDiceNumber());

        if (diceNumbers.get(0) == diceNumbers.get(1))
        {
            diceNumbers.add(diceNumbers.get(0));
            diceNumbers.add(diceNumbers.get(1));
        }
    }

    /**
     * Method to get {@link Turn#choosableFields}.
     * @return {@link Turn#choosableFields}.
     */
    public List<Integer> getChoosableFields()
    {
        return choosableFields;
    }

    /**
     * Method to set {@link Turn#choosableFields}.
     * @param choosableFields A {@link List} of Integers.
     */
    public void setChoosableFields(List<Integer> choosableFields)
    {
        this.choosableFields = choosableFields;
    }

    /**
     * Method to get the actual selection mode for the turn.
     * @return {@link Turn#mode}.
     */
    public Mode getMode()
    {
        return mode;
    }

    /**
     * Method to set the actual selection mode for the turn.
     * @param mode The new mode for the turn.
     */
    public void setMode(Mode mode)
    {
        this.mode = mode;
    }


}

