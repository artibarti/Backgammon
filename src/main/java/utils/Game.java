package utils;

import model.Board;
import model.Player;

public class Game
{
    public Game()
    {
        white = new Player("Player 1", 0);
        black = new Player("Player 2", 1);
        board = new Board();
    };

    private Player white;
    private Player black;

    private Board board;

    public void startGame()
    {
        
    }

}
