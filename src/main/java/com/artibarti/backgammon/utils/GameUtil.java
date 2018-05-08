package com.artibarti.backgammon.utils;

import com.artibarti.backgammon.model.Board;
import com.artibarti.backgammon.model.Field;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * Methods and constants representing the rules of the game Backgammon.
 */
public class GameUtil
{

    /**
     * Constant to describe whether a step took the last checker of a player to its base, or not.
     */
    public static final int NATURAL_STEP = 0;

    /**
     * Constant to describe whether a step took the last checker of a player to its base, or not.
     */
    public static final int WINNER_STEP = 1;

    /**
     * The constant value representing the first player.
     */
    public static final int Player1ID = 0;

    /**
     * The constant value representing the second player.
     */
    public static final int Player2ID = 1;

    /**
     * Initialize a {@link Board} according to the default state of a Backgammon board before the first step.
     * @param board The board.
     */
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

    /**
     * Returns the value representing the opponent player.
     * @param player The actual player.
     * @return The value representing the opponent player.
     */
    public static int getEnemyID(int player)
    {
        if (player == Player1ID)
            return Player2ID;
        if (player == Player2ID)
            return Player1ID;
        return -1;
    }

    /**
     * Generates a random {@link Integer} between 1 and 6.
     * @return A random {@link Integer} between 1 and 6.
     */
    public static int generateDiceNumber()
    {
        return ThreadLocalRandom.current().nextInt(1, 7);
    }

    /**
     * Move a checker on the board according to the given parameters.
     * @param board The board to make the step on.
     * @param from The ID of the start {@link Field}.
     * @param to The ID of the destination {@link Field}.
     * @param player The actual player represented by an {@link Integer}.
     * @return Can be {@link GameUtil#WINNER_STEP} or {@link GameUtil#NATURAL_STEP}.
     */
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

        return NATURAL_STEP;

    }

    /**
     * Method to get the fields can be stepped to on the board according to the given parameters.
     * @param board The board.
     * @param player The actual player.
     * @param from The ID of the source {@link Field}.
     * @param dicenumbers A {@link List} or Integers representing the dice numbers.
     * @return A {@link List} or Integers with the IDs of the {@link Field}s can be stepped on.
     */
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

        System.out.println(result);
        return result;
    }

    /**
     * Method to get the fields can be stepped from on the board according to the given parameters.
     * @param board The board.
     * @param player The actual player.
     * @param dicenumbers A {@link List} or Integers representing the dice numbers.
     * @return A {@link List} or Integers with the IDs of the {@link Field}s can be stepped from.
     */
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
            else
            {
                result.clear();
                return result;
            }
        }

        else
        {

            result = board.getFields().stream()
                    .filter(p -> p.getTeam() == player)
                    .map(Field::getId)
                    .collect(Collectors.toList());

            for (int i = 0; i < result.size(); i++)
            {
                if (getFieldsCanStepTo(board, player, result.get(i), dicenumbers).size() != 0)
                    return result;
            }

            result.clear();
            return result;
        }
    }

    /**
     * Observe whether a player on a board can start to bear its checkers to the base.
     * @param board The board.
     * @param player The actual player.
     * @return A boolean value describes whether a player can start to bear its checkers to the base.
     */
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

    /**
     * Observe whether a player on a board has already borne all its checkers.
     * @param board The board.
     * @param player The actual player.
     * @return A boolean value describes whether a player on the board has already borne all its checkers.
     */
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
