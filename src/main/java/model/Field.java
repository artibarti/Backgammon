package model;

import javax.xml.bind.annotation.XmlElement;

public class Field
{
    private int team;
    private int id;
    private int numberOfCheckers;

    public Field(int id)
    {
        numberOfCheckers = 0;
        this.id = id;
        team = -1;
    }

    public void addCheckers(int player, int count)
    {
        this.team = player;
        numberOfCheckers += count;
    }

    public void deleteChecker(int count)
    {
        numberOfCheckers -= count;
        if (numberOfCheckers <= 0)
            team = -1;
    }

    public void deleteCheckers()
    {
        numberOfCheckers = 0;
        team = -1;
    }

    @XmlElement(name = "team")
    public int getTeam()
    {
        return team;
    }

    @XmlElement(name = "numberOfCheckers")
    public int getNumberOfCheckers()
    {
        return numberOfCheckers;
    }

    @XmlElement(name = "id")
    public int getId()
    {
        return id;
    }
}
