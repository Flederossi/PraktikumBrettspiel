import com.flederossi.game.WinLogic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WinLogicTest {
    private final WinLogic winLogic;

    public WinLogicTest(){
        int[][] boardInit = {
                {1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1},
        };
        this.winLogic = new WinLogic(boardInit);
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void playerWon(int[][] board, int expected){
        this.winLogic.reloadPosBlack(board);
        assertEquals(expected, this.winLogic.checkWon(board));
    }

    private static Stream<Arguments> testCases(){
        return Stream.of(
                Arguments.arguments(new int[][]{
                        {1, 1, 1, 1, 2},
                        {1, 1, 1, 1, 2},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 2},
                        {1, 1, 1, 1, 1},
                }, 1),
                Arguments.arguments(new int[][]{
                        {0, 1, 1, 1, 1},
                        {2, 0, 1, 1, 1},
                        {0, 1, 0, 1, 1},
                        {1, 0, 2, 0, 0},
                        {1, 1, 0, 0, 2},
                }, 2),
                Arguments.arguments(new int[][]{
                        {1, 1, 2, 1, 2},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 2, 1, 1},
                        {1, 1, 1, 1, 1},
                }, 0),
                Arguments.arguments(new int[][]{
                        {1, 1, 1, 0, 2},
                        {1, 1, 0, 1, 0},
                        {1, 0, 2, 0, 1},
                        {0, 1, 0, 1, 1},
                        {2, 0, 1, 1, 1},
                }, 2)
        );
    }
}
