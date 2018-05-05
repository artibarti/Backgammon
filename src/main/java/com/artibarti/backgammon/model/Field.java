package com.artibarti.backgammon.model;

import javax.xml.bind.annotation.XmlAttribute;

/**
* Representation of a field on the Backgammon board.
*/
public class Field
{
    /**
     * Defines the player owns the field.
     */
    private int team;

    /**
     * The ID of the field.
     */
    private int id;

    /**
     * The number of checkers on the field.
     */
    private int numberOfCheckers;

    /**
     * Default constructor for {@link Field}.
     */
    public Field() {}

    /**
     * Constructor for {@link Field}.
     *
     * @param id The ID will be set as the ID of the field.
     */
    public Field(int id)
    {
        numberOfCheckers = 0;
        this.id = id;
        team = -1;
    }

    /**
     * Add checkers to the field.
     *
     * @param player The player owns the checkers.
     * @param count The number of the checkers.
     */
    public void addCheckers(int player, int count)
    {
        this.team = player;
        numberOfCheckers += count;
    }

    /**
     * Delete checkers from the {@link Field}.
     *
     * @param count The number of checkers to delete.
     */
    public void deleteChecker(int count)
    {
        numberOfCheckers -= count;
        if (numberOfCheckers <= 0)
            team = -1;
    }

    /**
     * Delete all checkers on board, the team of the field will be set to -1.
     */
    public void deleteCheckers()
    {
        numberOfCheckers = 0;
        team = -1;
    }

    /**
     * Methos to get the player owns the field.
     *
     * @return The ID of the player owns the field.
     */
    @XmlAttribute(name = "team")
    public int getTeam()
    {
        return team;
    }

    /**
     * Method to get the number of the checkers on the field.
     *
     * @return The number of the checkers on the field.
     */
    @XmlAttribute(name = "numberOfCheckers")
    public int getNumberOfCheckers()
    {
        return numberOfCheckers;
    }

    /**
     * Method to get the ID for the field.
     *
     * @return The ID of the field.
     */
    @XmlAttribute(name = "id")
    public int getId()
    {
        return id;
    }
}
