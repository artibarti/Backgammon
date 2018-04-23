package utils;

import model.Board;
import model.Player;

import java.util.concurrent.ThreadLocalRandom;

public class Game
{
    private Player player1;
    private Player player2;
    private Board board;

    public Game()
    {
        board = new Board();
    };

    public void startGame()
    {
        
    }

    public Player getPlayer1()
    {
        return player1;
    }
    public Player getPlayer2()
    {
        return player2;
    }

    public void setPlayer1(Player player1)
    {
        this.player1 = player1;
    }
    public void setPlayer2(Player player2)
    {
        this.player2 = player2;
    }

    public Board getBoard()
    {
        return board;
    }

    public boolean canStep(int from, int to, int player)
    {
        if (player == 0 && from > to)   return false;
        if (player == 1 && to > from)   return false;
        if (board.getFields().get(from).getTeam() != player)    return false;
        if (board.getFields().get(to).getTeam() == player && board.getFields().get(to).getCheckers().size() == 5) return  false;
        if (board.getFields().get(to).getTeam() != player && board.getFields().get(to).getCheckers().size() > 1) return false;

        return true;
    }

    public void step(int from, int to, int player)
    {
        board.getFields().get(from).deleteChecker(1);
        board.getFields().get(to).addCheckers(player, 1);
    }

    public int getNumber()
    {
        return ThreadLocalRandom.current().nextInt(1, 7);
    }
}
