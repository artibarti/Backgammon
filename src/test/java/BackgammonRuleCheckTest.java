import com.artibarti.backgammon.model.Board;
import com.artibarti.backgammon.utils.GameUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class BackgammonRuleCheckTest
{

    @Test
    public void TestCanStepFromRule()
    {
        Board board = new Board();
        GameUtil.initBoard(board);

        List<Integer> dicenumbers = new ArrayList<>();
        dicenumbers.add(1);
        dicenumbers.add(2);

        List<Integer> fieldsCanStepFrom = GameUtil.getFieldsCanStepFrom(board, GameUtil.Player1ID, dicenumbers);

        assertEquals(4, fieldsCanStepFrom.size());
        assertEquals(1, fieldsCanStepFrom.get(0).intValue());
        assertEquals(12, fieldsCanStepFrom.get(1).intValue());
        assertEquals(17, fieldsCanStepFrom.get(2).intValue());
        assertEquals(19, fieldsCanStepFrom.get(3).intValue());
    }

    @Test
    public void TestGenarateDiceNumber()
    {
        for (int i = 0; i < 100; i++)
        {
            int num = GameUtil.generateDiceNumber();
            Assert.assertTrue(num > 0 && num < 7);
        }
    }

    @Test
    public void TestCanStepToRule()
    {
        Board board = new Board();
        GameUtil.initBoard(board);

        List<Integer> dicenumbers = new ArrayList<>();
        dicenumbers.add(5);
        dicenumbers.add(1);

        List<Integer> fieldsCanStepTo = GameUtil.getFieldsCanStepTo(board, GameUtil.Player1ID, 12, dicenumbers);

        assertEquals(1, fieldsCanStepTo.size());
        assertEquals(17, fieldsCanStepTo.get(0).intValue());
    }

    @Test
    public void TestInitBoard()
    {
        Board board = new Board();
        GameUtil.initBoard(board);

        assertEquals(2, board.getField(1).getNumberOfCheckers());
        assertEquals(5, board.getField(6).getNumberOfCheckers());
        assertEquals(3, board.getField(8).getNumberOfCheckers());
        assertEquals(5, board.getField(12).getNumberOfCheckers());
        assertEquals(5, board.getField(13).getNumberOfCheckers());
        assertEquals(3, board.getField(17).getNumberOfCheckers());
        assertEquals(5, board.getField(19).getNumberOfCheckers());
        assertEquals(2, board.getField(24).getNumberOfCheckers());

        assertEquals(0, board.getField(1).getTeam());
        assertEquals(1, board.getField(6).getTeam());
        assertEquals(1, board.getField(8).getTeam());
        assertEquals(0, board.getField(12).getTeam());
        assertEquals(1, board.getField(13).getTeam());
        assertEquals(0, board.getField(17).getTeam());
        assertEquals(0, board.getField(19).getTeam());
        assertEquals(1, board.getField(24).getTeam());
    }

    @Test
    public void TestGetEnemyID()
    {
        assertEquals(1, GameUtil.getEnemyID(GameUtil.Player1ID));
        assertEquals(0, GameUtil.getEnemyID(GameUtil.Player2ID));
    }

    @Test
    public void TestCanBearingOff()
    {
        Board board = new Board();
        board.getField(1).addCheckers(GameUtil.Player1ID, 1);
        board.getField(24).addCheckers(GameUtil.Player1ID, 4);
        assertEquals(false, GameUtil.canBearingOff(board, GameUtil.Player1ID) );
        board.getField(1).deleteCheckers();
        board.getField(24).addCheckers(GameUtil.Player1ID, 4);
        assertEquals(true, GameUtil.canBearingOff(board, GameUtil.Player1ID) );

    }

    @Test
    public void TestStep()
    {
        Board board = new Board();
        board.getField(1).addCheckers(GameUtil.Player1ID, 1);
        board.getField(24).addCheckers(GameUtil.Player1ID, 4);

        GameUtil.step(board, 1, 2, GameUtil.Player1ID);
        assertEquals(0, board.getField(1).getNumberOfCheckers() );
        assertEquals(1, board.getField(2).getNumberOfCheckers() );
        assertEquals(-1, board.getField(1).getTeam());
        assertEquals(GameUtil.Player1ID, board.getField(2).getTeam());
    }

    @Test
    public void TestIsWinner()
    {
        Board board = new Board();
        GameUtil.initBoard(board);
        assertEquals(false, GameUtil.isWinner(board, GameUtil.Player1ID) );

        board = new Board();
        assertEquals(true, GameUtil.isWinner(board, GameUtil.Player1ID) );
    }

}