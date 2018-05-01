package model;

import utils.GameUtil;
import java.util.ArrayList;
import java.util.List;

public class Turn
{

    public enum Mode{ SELECT_FROM, SELECT_TO };

    private Mode mode;
    private int from;
    private int player;
    private List<Integer> diceNumbers;
    private List<Integer> choosableFields;

    public Turn(int player, Board board)
    {
        mode = Mode.SELECT_FROM;
        choosableFields = new ArrayList<>();
        this.player = player;
        initDiceNumbers();
    }

    public int getStepsLeft()
    {
        return diceNumbers.size();
    }

    public int getPlayer()
    {
        return player;
    }

    public void setFrom(int from)
    {
        this.from = from;
        mode = Mode.SELECT_TO;
    }

    public int getFrom()
    {
        return from;
    }

    public List<Integer> getDiceNumbers()
    {
        return diceNumbers;
    }

    public void removeStep(int step)
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

    public List<Integer> getChoosableFields()
    {
        return choosableFields;
    }

    public void setChoosableFields(List<Integer> choosableFields)
    {
        this.choosableFields = choosableFields;
    }

    public Mode getMode()
    {
        return mode;
    }

    public void setMode(Mode mode)
    {
        this.mode = mode;
    }


}

