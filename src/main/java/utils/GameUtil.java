package utils;

import model.Board;
import model.Field;
import model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

public class GameUtil
{

    public static void initBoard(Board board, Player p1, Player p2)
    {
        board.getFields().get(0).addCheckers(p1, 2);
        board.getFields().get(5).addCheckers(p2, 5);
        board.getFields().get(7).addCheckers(p2, 3);
        board.getFields().get(11).addCheckers(p1, 5);

        board.getFields().get(12).addCheckers(p2, 5);
        board.getFields().get(16).addCheckers(p1, 3);
        board.getFields().get(18).addCheckers(p1, 5);
        board.getFields().get(23).addCheckers(p2, 2);
    }

    public static int getPlayer1ID()
    {
        return 0;
    }

    public static int getPlayer2ID()
    {
        return 1;
    }

    public static int generateDiceNumber()
    {
        return ThreadLocalRandom.current().nextInt(1, 7);
    }

    public static void step(Board board, int from, int to, Player player)
    {
        board.getFields().get(from).deleteChecker(1);
        board.getFields().get(to).addCheckers(player, 1);
    }

    public static List<Integer> getFieldsCanStepFrom(Board board, Player player, List<Integer> dicenumbers)
    {
        return board.getFields().stream()
                .filter(p -> p.getTeam() == player.getTeam())
                .filter(p -> dicenumbers.stream().anyMatch
                        (
                                q -> (p.getId() + q <= 23
                                        && board.getFields().get(p.getId() + q).getTeam() == getPlayer1ID()
                                        && p.getTeam() == getPlayer1ID()
                                        && board.getFields().get(p.getId() + q).getNumberOfCheckers() < 5) ||
                                     (p.getId() + q <= 23
                                        && board.getFields().get(p.getId() + q).getTeam() != getPlayer1ID()
                                        && p.getTeam() == getPlayer1ID()
                                        && board.getFields().get(p.getId() + q).getNumberOfCheckers() < 2) ||
                                     (p.getId() - q >= 0
                                        && board.getFields().get(p.getId() - q).getTeam() == getPlayer2ID()
                                        && p.getTeam() == getPlayer2ID()
                                        && board.getFields().get(p.getId() - q).getNumberOfCheckers() < 5) ||
                                     (p.getId() - q >= 0
                                        && board.getFields().get(p.getId() - q).getTeam() != getPlayer2ID()
                                        && p.getTeam() == getPlayer2ID()
                                        && board.getFields().get(p.getId() - q).getNumberOfCheckers() < 2)

                        ))
                .map(Field::getId)
                .collect(Collectors.toList());
    }

    public static List<Integer> getFieldsCanStepTo(Board board, Player player, int from, List<Integer> dicenumbers)
    {
        System.out.println("number of fields can step to: " +
                board.getFields().stream()
                        .filter(
                                p -> (p.getTeam() != player.getTeam() && p.getNumberOfCheckers() < 2) ||
                                        (p.getTeam() == player.getTeam() && p.getNumberOfCheckers() < 5)
                        )
                        .filter(p -> ((p.getId() < from) && (p.getTeam() != 0)) || ((p.getId() > from) && (p.getTeam() != 1)))
                        .map(m -> m.getId())
                        .filter(p -> dicenumbers.stream().anyMatch(q -> q == abs(p - from)) )
                        .collect(Collectors.toList()).size()
        );

        return board.getFields().stream()
                .filter(
                        p -> (p.getTeam() != player.getTeam() && p.getNumberOfCheckers() < 2) ||
                                (p.getTeam() == player.getTeam() && p.getNumberOfCheckers() < 5)
                        )
                .filter(p -> ((p.getId() < from) && (p.getTeam() != 0)) || ((p.getId() > from) && (p.getTeam() != 1)))
                .map(m -> m.getId())
                .filter(p -> dicenumbers.stream().anyMatch(q -> q == abs(p - from)) )
                .collect(Collectors.toList());
    }

    public static String getColorForDices(Player player)
    {
        if (player.getTeam() == getPlayer1ID())
            return "white";
        if (player.getTeam() == getPlayer2ID())
            return "black";

        return null;
    }
}
