import com.flederossi.game.Board;
import com.flederossi.game.WinLogic;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WinLogicTest {
    private final WinLogic winLogic;

    public WinLogicTest() {
        int[][] boardInit = {
                {1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1},
        };
        this.winLogic = new WinLogic(new Board(boardInit));
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testPlayerWon(int[][] board, int expected) {
        this.winLogic.reloadPosBlack(new Board(board));
        assertEquals(expected, this.winLogic.checkWon(new Board(board)));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                arguments(new int[][]{
                        {1, 1, 1, 1, 2},
                        {1, 1, 1, 1, 2},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 2},
                        {1, 1, 1, 1, 1},
                }, 1),
                arguments(new int[][]{
                        {0, 1, 1, 1, 1},
                        {2, 0, 1, 1, 1},
                        {0, 1, 0, 1, 1},
                        {1, 0, 2, 0, 0},
                        {1, 1, 0, 0, 2},
                }, 2),
                arguments(new int[][]{
                        {1, 1, 2, 1, 2},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 2, 1, 1},
                        {1, 1, 1, 1, 1},
                }, 0),
                arguments(new int[][]{
                        {1, 1, 1, 0, 2},
                        {1, 1, 0, 1, 0},
                        {1, 0, 2, 0, 1},
                        {0, 1, 0, 1, 1},
                        {2, 0, 1, 1, 1},
                }, 2),
                arguments(new int[][]{
                        {1, 1, 1, 1, 2},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {2, 1, 1, 1, 2},
                }, 0),
                arguments(new int[][]{
                        {1, 1, 1, 1, 1},
                        {1, 1, 0, 1, 1},
                        {1, 0, 2, 0, 1},
                        {0, 0, 0, 1, 1},
                        {2, 2, 0, 1, 1},
                }, 2)
        );
    }
}
