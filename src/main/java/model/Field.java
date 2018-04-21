package model;

import java.util.ArrayList;
import java.util.List;

public class Field
{
    private int id;

    public Field(int id)
    {
        this.id = id;
        checkers = new ArrayList<>();
    }

    private List<Checker> checkers;

    public List<Checker> getCheckers()
    {
        return checkers;
    }

    public void addCheckers(int team, int count)
    {
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

}
