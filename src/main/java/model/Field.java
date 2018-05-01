package model;

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

    public int getTeam()
    {
        return team;
    }

    public int getNumberOfCheckers()
    {
        return numberOfCheckers;
    }

    public int getId()
    {
        return id;
    }
}
