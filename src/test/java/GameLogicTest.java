import com.flederossi.game.Board;
import com.flederossi.game.Move;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameLogicTest {

    private Board board;

    public GameLogicTest(){
        int[][] boardInit = {
                {1, 1, 1, 1, 2},
                {1, 1, 1, 1, 1},
                {1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1},
                {2, 1, 1, 1, 1},
        };
        this.board = new Board(boardInit);
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testLegalMoves(int[][] board, Move move, boolean expected){
        this.board = new Board(board);
        assertEquals(expected, this.board.checkLegalMove(move));
    }

    private static Stream<Arguments> testCases(){
        return Stream.of(
                arguments(new int[][]{
                        {1, 1, 1, 0, 2},
                        {1, 0, 1, 1, 1},
                        {1, 1, 2, 1, 1},
                        {1, 1, 0, 1, 1},
                        {1, 2, 1, 1, 0},
                }, new Move(2, 0, 1, 4, 0), true),
                arguments(new int[][]{
                        {1, 1, 1, 1, 1},
                        {1, 1, 0, 1, 1},
                        {1, 0, 2, 0, 1},
                        {1, 1, 0, 1, 1},
                        {1, 1, 1, 1, 1},
                }, new Move(2, 0, 1, 2, 2), false),
                arguments(new int[][]{
                        {1, 2, 1, 0, 1},
                        {1, 1, 2, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                }, new Move(1, 1, 0, 2, 0), true),
                arguments(new int[][]{
                        {1, 1, 1, 1, 1},
                        {1, 1, 2, 1, 1},
                        {1, 0, 1, 0, 1},
                        {1, 1, 0, 1, 1},
                        {1, 1, 1, 1, 1},
                }, new Move(1, 0, -1, 2, 2), false),
                arguments(new int[][]{
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 2, 1},
                        {1, 1, 1, 1, 1},
                }, new Move(2, -2, 0, 3, 3), false)
        );
    }
}
