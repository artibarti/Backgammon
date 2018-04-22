package model;

import java.util.ArrayList;
import java.util.List;

public class Field
{
    private int team;

    public Field()
    {
        checkers = new ArrayList<>();
        team = -1;
    }

    private List<Checker> checkers;

    public List<Checker> getCheckers()
    {
        return checkers;
    }

    public void addCheckers(int team, int count)
    {
        this.team = team;

        for (int i = 0; i<count; i++)
        {
            checkers.add(new Checker(team));
        }
    }

    public void detractCheckers(int count)
    {
        for (int i = 0; i<count; i++)
        {
            if (checkers.size() != 0)
                checkers.remove(checkers.size() - 1);
        }
    }

    public int getTeam()
    {
        return team;
    }

}
