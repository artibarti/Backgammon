package model;

import model.Player;
import utils.GameUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Turn
{

    private int from;
    private Player player;
    private List<Integer> diceNumbers;

    public Turn(Player player)
    {
        this.player = player;
        initDiceNumbers();
        nextStep();
    }

    public int getStepsLeft()
    {
        return diceNumbers.size();
    }

    public List<Integer> getSteps()
    {
        return diceNumbers;
    }

    public Player getPlayer()
    {
        return player;
    }

    public void setFrom(int from)
    {
        this.from = from;
    }

    public int getFrom()
    {
        return from;
    }

    public List<Integer> getDiceNumbers()
    {
        return diceNumbers;
    }

    public void nextStep()
    {
        from = -1;
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

}

