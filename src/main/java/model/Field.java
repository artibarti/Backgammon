package model;

import java.util.List;

public class Field
{
    private int id;

    public Field(int id)
    {
        this.id = id;
    }

    private List<Checker> checkers;

    public List<Checker> getCheckers()
    {
        return checkers;
    }

    public void addChecker(Checker checker)
    {
        checkers.add(checker);
    }

    public void detractChecker(Checker checker)
    {
        if (checkers.size() != 0)
            checkers.remove(checkers.size() - 1);
    }

}
