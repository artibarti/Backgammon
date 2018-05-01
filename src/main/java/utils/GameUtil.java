package utils;

import model.Board;
import model.Field;
import model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class GameUtil
{

    public static void initBoard(Board board, Player p1, Player p2)
    {
        board.getFields().get(0).addCheckers(getPlayer1ID(), 2);
        board.getFields().get(5).addCheckers(getPlayer2ID(), 5);
        board.getFields().get(7).addCheckers(getPlayer2ID(), 3);
        board.getFields().get(11).addCheckers(getPlayer1ID(), 5);

        board.getFields().get(12).addCheckers(getPlayer2ID(), 5);
        board.getFields().get(16).addCheckers(getPlayer1ID(), 3);
        board.getFields().get(18).addCheckers(getPlayer1ID(), 5);
        board.getFields().get(23).addCheckers(getPlayer2ID(), 2);
    }

    public static int getPlayer1ID()
    {
        return 0;
    }

    public static int getPlayer2ID()
    {
        return 1;
    }

    public static int getEnemyID(int player)
    {
        if (player == getPlayer1ID())
            return getPlayer2ID();
        if (player == getPlayer2ID())
            return getPlayer1ID();
        return -1;
    }

    public static int generateDiceNumber()
    {
        return ThreadLocalRandom.current().nextInt(1, 7);
    }

    public static void step(Board board, int from, int to, int player)
    {

        if (from == 0 && player == getPlayer1ID())
        {
            board.minusKickedChecker(player);
        }

        else if (from == 25 && player == getPlayer2ID())
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
    }

    public static List<Integer> canStepFrom(Board board, int from, int player, List<Integer> diceNumbers)
    {
        if (board.getFields().get(from).getTeam() == player)
        {
            return getFieldsCanStepTo(board, player, from, diceNumbers);
        }

        return null;
    }

    public static List<Integer> getFieldsCanStepTo(Board board, int player, int from, List<Integer> dicenumbers)
    {

        if (player == getPlayer1ID())
        {
            return board.getFields().stream()
                    .filter(p -> p.getId() > from)
                    .filter(p -> dicenumbers.stream().anyMatch(q -> from + q == p.getId())
                            && ((p.getTeam() == player && p.getNumberOfCheckers() < 5)
                            || (p.getTeam() != player && p.getNumberOfCheckers() < 2)))
                    .map(m -> m.getId())
                    .collect(Collectors.toList());
        }

        if (player == getPlayer2ID())
        {
            return board.getFields().stream()
                    .filter(p -> p.getId() < from)
                    .filter(p -> dicenumbers.stream().anyMatch(q -> from - q == p.getId())
                            && ((p.getTeam() == player && p.getNumberOfCheckers() < 5)
                            || (p.getTeam() != player && p.getNumberOfCheckers() < 2)))
                    .map(m -> m.getId())
                    .collect(Collectors.toList());
        }

        return  null;
    }

    public static List<Integer> getFieldsCanStepFrom(Board board, int player)
    {
        if (board.getKickedCheckers(player) != 0)
        {
            List result = new ArrayList();

            if (player == getPlayer1ID())
            {
                result.add(0);
                return result;
            }
            if (player == getPlayer1ID())
            {
                result.add(25);
                return result;
            }
        }

        return board.getFields().stream()
                .filter(p -> p.getTeam() == player)
                .map(Field::getId)
                .collect(Collectors.toList());
    }

    public static boolean canBearingOff(Board board, int player)
    {
        if (board.getKickedCheckers(player) != 0)
            return false;

        if ((player == getPlayer1ID() && (board.getFields().stream().filter(p -> p.getId() < 18).anyMatch(p -> p.getTeam() == player)))
                || player == getPlayer2ID() && board.getFields().stream().filter(p -> p.getId() > 5).anyMatch(p -> p.getTeam() == player))
        {
                return false;
        }

        return true;
    }

}
