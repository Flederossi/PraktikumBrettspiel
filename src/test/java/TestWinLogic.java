import com.flederossi.game.WinLogic;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestWinLogic {
    WinLogic winLogic;

    TestWinLogic(){
        int[][] boardInit = {
                {1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1},
        };
        this.winLogic = new WinLogic(boardInit);
    }

    @Test
    void testWin(){

        int[][][] testCases = {{    {1, 1, 1, 1, 2},
                                    {1, 1, 1, 1, 2},
                                    {1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 2},
                                    {1, 1, 1, 1, 1}     },

                               {    {0, 1, 1, 1, 1},
                                    {2, 0, 1, 1, 1},
                                    {0, 1, 0, 1, 1},
                                    {1, 0, 2, 0, 0},
                                    {1, 1, 0, 0, 2}     },

                               {    {1, 1, 2, 1, 2},
                                    {1, 1, 1, 1, 1},
                                    {1, 1, 1, 1, 1},
                                    {1, 1, 2, 1, 1},
                                    {1, 1, 1, 1, 1}     },

                               {    {1, 1, 1, 0, 2},
                                    {1, 1, 0, 1, 0},
                                    {1, 0, 2, 0, 1},
                                    {0, 1, 0, 1, 1},
                                    {2, 0, 1, 1, 1}     }};

        int[] expected = {1, 2, 0, 2};

        for (int i = 0; i < testCases.length; i++){
            this.winLogic.reloadPosBlack(testCases[i]);
            assertEquals(expected[i], winLogic.checkWon(testCases[i]));
        }
    }
}
