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
        List<Integer> dicenumbers = new ArrayList<>();
        dicenumbers.add(1);
        dicenumbers.add(2);

        GameUtil.initBoard(board);

        assertEquals(4, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player1ID, dicenumbers).size());
        assertEquals(1, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player1ID, dicenumbers).get(0).intValue());
        assertEquals(12, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player1ID, dicenumbers).get(1).intValue());
        assertEquals(17, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player1ID, dicenumbers).get(2).intValue());
        assertEquals(19, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player1ID, dicenumbers).get(3).intValue());

        assertEquals(4, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player2ID, dicenumbers).size());
        assertEquals(6, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player2ID, dicenumbers).get(0).intValue());
        assertEquals(8, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player2ID, dicenumbers).get(1).intValue());
        assertEquals(13, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player2ID, dicenumbers).get(2).intValue());
        assertEquals(24, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player2ID, dicenumbers).get(3).intValue());

        board.clear();
        board.addKickedChecker(GameUtil.Player1ID);
        board.addKickedChecker(GameUtil.Player2ID);
        assertEquals(0, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player1ID, dicenumbers).get(0).intValue());
        assertEquals(25, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player2ID, dicenumbers).get(0).intValue());
        board.addChecker(1, GameUtil.Player2ID);
        board.addChecker(1, GameUtil.Player2ID);
        board.addChecker(2, GameUtil.Player2ID);
        board.addChecker(2, GameUtil.Player2ID);
        board.addChecker(23, GameUtil.Player1ID);
        board.addChecker(23, GameUtil.Player1ID);
        board.addChecker(24, GameUtil.Player1ID);
        board.addChecker(24, GameUtil.Player1ID);

        assertEquals(0, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player1ID, dicenumbers).size());
        assertEquals(0, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player2ID, dicenumbers).size());

        assertEquals(0, GameUtil.getFieldsCanStepFrom(board, 123, dicenumbers).size());

        board.clear();
        board.addChecker(1, GameUtil.Player1ID);
        board.addChecker(2, GameUtil.Player2ID);
        board.addChecker(2, GameUtil.Player2ID);
        board.addChecker(3, GameUtil.Player2ID);
        board.addChecker(3, GameUtil.Player2ID);
        //assertEquals(0, GameUtil.getFieldsCanStepFrom(board, GameUtil.Player1ID, dicenumbers).size());

        board.addKickedChecker(GameUtil.Player1ID);
        assertEquals(0, GameUtil.getFieldsCanStepFrom(board, 123, dicenumbers).size());

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

        board = new Board();
        board.addChecker(24, GameUtil.Player1ID);
        fieldsCanStepTo = GameUtil.getFieldsCanStepTo(board, GameUtil.Player1ID, 24, dicenumbers);
        assertEquals(1, fieldsCanStepTo.size());
        assertEquals(25, fieldsCanStepTo.get(0).intValue());

        board = new Board();
        board.addChecker(0, GameUtil.Player2ID);
        fieldsCanStepTo = GameUtil.getFieldsCanStepTo(board, GameUtil.Player2ID, 1, dicenumbers);
        assertEquals(1, fieldsCanStepTo.size());
        assertEquals(0, fieldsCanStepTo.get(0).intValue());

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
        assertEquals(-1, GameUtil.getEnemyID(2));
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

        board = new Board();
        board.addKickedChecker(GameUtil.Player1ID);
        assertEquals(false, GameUtil.canBearingOff(board, GameUtil.Player1ID) );
    }

    @Test
    public void TestStep()
    {
        Board board = new Board();
        board.getField(1).addCheckers(GameUtil.Player1ID, 1);
        board.getField(5).addCheckers(GameUtil.Player2ID, 1);
        assertEquals(GameUtil.NATURAL_STEP, GameUtil.step(board, 1, 2, GameUtil.Player1ID));
        assertEquals(GameUtil.NATURAL_STEP, GameUtil.step(board, 5, 3, GameUtil.Player2ID));

        board = new Board();
        board.getField(24).addCheckers(GameUtil.Player1ID, 2);
        assertEquals(GameUtil.NATURAL_STEP, GameUtil.step(board, 24, 25, GameUtil.Player1ID));
        assertEquals(GameUtil.WINNER_STEP, GameUtil.step(board, 24, 25, GameUtil.Player1ID));

        board = new Board();
        board.getField(1).addCheckers(GameUtil.Player2ID, 2);
        assertEquals(GameUtil.NATURAL_STEP, GameUtil.step(board, 1, 0, GameUtil.Player2ID));
        assertEquals(1, board.getField(1).getNumberOfCheckers());
        assertEquals(GameUtil.WINNER_STEP, GameUtil.step(board, 1, 0, GameUtil.Player2ID));

        board.addKickedChecker(GameUtil.Player1ID);
        assertEquals(1, board.getKickedCheckers(GameUtil.Player1ID));
        assertEquals(GameUtil.NATURAL_STEP, GameUtil.step(board, 0, 2, GameUtil.Player1ID));
        assertEquals(0, board.getKickedCheckers(GameUtil.Player1ID));

        board.getField(1).deleteCheckers();
        board.getField(1).addCheckers(GameUtil.Player1ID, 1);
        board.getField(2).addCheckers(GameUtil.Player2ID, 3);

        assertEquals(GameUtil.NATURAL_STEP, GameUtil.step(board, 2, 1, GameUtil.Player2ID));

    }

    @Test
    public void TestIsWinner()
    {
        Board board = new Board();
        GameUtil.initBoard(board);
        assertEquals(false, GameUtil.isWinner(board, GameUtil.Player1ID) );

        board = new Board();
        assertEquals(true, GameUtil.isWinner(board, GameUtil.Player1ID) );

        board.addKickedChecker(GameUtil.Player1ID);
        assertEquals(false, GameUtil.isWinner(board, GameUtil.Player1ID) );

    }

}
