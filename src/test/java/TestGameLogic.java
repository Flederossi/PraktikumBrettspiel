import com.flederossi.game.Game;
import com.flederossi.game.Move;
import com.flederossi.game.WinLogic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGameLogic {

    Game game;

    TestGameLogic(){
        int[][] boardInit = {
                {1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1},
        };
        this.game = new Game(boardInit);
    }

    @Test
    void testLegalMove(){
        int[][][] testCasesBoard = {
                {
                        {1, 1, 1, 0, 2},
                        {1, 0, 1, 1, 1},
                        {1, 1, 2, 1, 1},
                        {1, 1, 0, 1, 1},
                        {1, 2, 1, 1, 0},
                },
                {
                        {1, 1, 1, 1, 1},
                        {1, 1, 0, 1, 1},
                        {1, 0, 2, 0, 1},
                        {1, 1, 0, 1, 1},
                        {1, 1, 1, 1, 1},
                },
                {
                        {1, 2, 1, 0, 1},
                        {1, 1, 2, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                },
                {
                        {1, 1, 1, 1, 1},
                        {1, 1, 2, 1, 1},
                        {1, 0, 1, 0, 1},
                        {1, 1, 0, 1, 1},
                        {1, 1, 1, 1, 1},
                },
                {
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 2, 1},
                        {1, 1, 1, 1, 1},
                }
        };
        Move[] testCasesMove = {
                new Move(2, 1, 0, 0, 4),
                new Move(2, 1, 0, 2, 2),
                new Move(1, 0, 1, 0, 2),
                new Move(1, -1, 0, 2, 2),
                new Move(2, 0, -2, 3, 3)
        };

        boolean[] expected = {true, false, true, false, false};

        for (int i = 0; i < testCasesBoard.length; i++) {
            assertEquals(expected[i], this.game.checkLegalMove(testCasesMove[i], testCasesBoard[i]));
        }
    }
}
