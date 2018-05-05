package com.artibarti.backgammon.utils;

import com.artibarti.backgammon.model.Board;
import com.artibarti.backgammon.model.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GameUtil
{

    public static final int NATURAL_STEP = 0;
    public static final int WINNER_STEP = 1;
    public static final int Player1ID = 0;
    public static final int Player2ID = 1;

    public static void initBoard(Board board)
    {
        board.getFields().get(0).addCheckers(Player1ID, 2);
        board.getFields().get(5).addCheckers(Player2ID, 5);
        board.getFields().get(7).addCheckers(Player2ID, 3);
        board.getFields().get(11).addCheckers(Player1ID, 5);

        board.getFields().get(12).addCheckers(Player2ID, 5);
        board.getFields().get(16).addCheckers(Player1ID, 3);
        board.getFields().get(18).addCheckers(Player1ID, 5);
        board.getFields().get(23).addCheckers(Player2ID, 2);
    }

    public static int getEnemyID(int player)
    {
        if (player == Player1ID)
            return Player2ID;
        if (player == Player2ID)
            return Player1ID;
        return -1;
    }

    public static int generateDiceNumber()
    {
        return ThreadLocalRandom.current().nextInt(1, 7);
    }

    public static int step(Board board, int from, int to, int player)
    {
        if (to == 25 && player == Player1ID || to == 0 && player == Player2ID)
        {
            board.addBorneChecker(player);
            board.deleteChecker(from, 1);

            if (isWinner(board, player))
                return WINNER_STEP;
            else
                return NATURAL_STEP;
        }

        if (from == 0 && player == Player1ID || from == 25 && player == Player2ID)
        {
            board.minusKickedChecker(player);
        }
        else
        {
            board.deleteChecker(from, 1);
        }


        if (board.getField(to).getTeam() != player)
        {
            if (board.getField(to).getNumberOfCheckers() != 0)
            {
                board.deleteCheckers(to);
                board.addKickedChecker(getEnemyID(player));
            }
        }

        board.addChecker(to, player);

        if (isWinner(board, player))
            return WINNER_STEP;
        else
            return NATURAL_STEP;

    }

    public static List<Integer> getFieldsCanStepTo(Board board, int player, int from, List<Integer> dicenumbers)
    {

        List<Integer> result = new ArrayList<>();

        if (player == Player1ID)
        {
            result = board.getFields().stream()
                    .filter(p -> p.getId() > from)
                    .filter(p -> dicenumbers.stream().anyMatch(q -> from + q == p.getId())
                            && ((p.getTeam() == player && p.getNumberOfCheckers() < 5)
                            || (p.getTeam() != player && p.getNumberOfCheckers() < 2)))
                    .map(m -> m.getId())
                    .collect(Collectors.toList());

            if (canBearingOff(board, player) && dicenumbers.stream().anyMatch(p -> from + p >= 25))
            {
                result.add(25);
            }
        }

        if (player == Player2ID)
        {
            result = board.getFields().stream()
                    .filter(p -> p.getId() < from)
                    .filter(p -> dicenumbers.stream().anyMatch(q -> from - q == p.getId())
                            && ((p.getTeam() == player && p.getNumberOfCheckers() < 5)
                            || (p.getTeam() != player && p.getNumberOfCheckers() < 2)))
                    .map(m -> m.getId())
                    .collect(Collectors.toList());

            if (canBearingOff(board, player) && dicenumbers.stream().anyMatch(p -> from - p <= 0))
            {
                result.add(0);
            }

        }

        return result;
    }

    public static List<Integer> getFieldsCanStepFrom(Board board, int player, List<Integer> dicenumbers)
    {

        List<Integer> result = new ArrayList();

        if (board.getKickedCheckers(player) != 0)
        {
            if (player == Player1ID)
            {
                result.add(0);

                if(getFieldsCanStepTo(board,player,0, dicenumbers).size() != 0)
                    return result;
                else
                {
                    result.clear();
                    return result;
                }
            }
            if (player == Player2ID)
            {
                result.add(25);

                if(getFieldsCanStepTo(board,player,25, dicenumbers).size() != 0)
                    return result;
                else
                {
                    result.clear();
                    return result;
                }
            }
        }

        result = board.getFields().stream()
                .filter(p -> p.getTeam() == player)
                .map(Field::getId)
                .collect(Collectors.toList());

        for (int i = 0; i<result.size(); i++)
        {
            if (getFieldsCanStepTo(board, player, result.get(i), dicenumbers).size() != 0)
                return result;
        }

        result.clear();
        return result;
    }

    public static boolean canBearingOff(Board board, int player)
    {
        if (board.getKickedCheckers(player) != 0)
            return false;

        if ((player == Player1ID && (board.getFields().stream().filter(p -> p.getId() < 19).anyMatch(p -> p.getTeam() == player)))
                || player == Player2ID && board.getFields().stream().filter(p -> p.getId() > 6).anyMatch(p -> p.getTeam() == player))
        {
                return false;
        }

        return true;
    }


    public static boolean isWinner(Board board, int player)
    {
        if (board.getFields().stream().anyMatch(p -> p.getTeam() == player) == true)
        {
            return false;
        }

        if (board.getKickedCheckers(player) != 0)
        {
            return false;
        }

        return true;
    }
}
