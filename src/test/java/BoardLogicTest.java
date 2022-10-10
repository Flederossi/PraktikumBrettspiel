import com.flederossi.game.Board;
import com.flederossi.game.Coordinate;
import com.flederossi.game.Move;
import org.junit.jupiter.params.ParameterizedTest;

import static org.junit.jupiter.params.provider.Arguments.arguments;

import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardLogicTest {

    private Board board;

    public BoardLogicTest() {
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
    public void testLegalMoves(int[][] board, Move move, int player, boolean expected) {
        this.board = new Board(board);
        assertEquals(expected, this.board.checkLegalMove(move, player));
    }

    private static Stream<Arguments> testCases() {
        return Stream.of(
                arguments(new int[][]{
                        {1, 1, 1, 0, 2},
                        {1, 0, 1, 1, 1},
                        {1, 1, 2, 1, 1},
                        {1, 1, 0, 1, 1},
                        {1, 2, 1, 1, 0},
                }, new Move(new Coordinate(4, 0), new Coordinate(4, 1)), 2, true),
                arguments(new int[][]{
                        {1, 1, 1, 1, 1},
                        {1, 1, 0, 1, 1},
                        {1, 0, 2, 0, 1},
                        {1, 1, 0, 1, 1},
                        {1, 1, 1, 1, 1},
                }, new Move(new Coordinate(2, 2), new Coordinate(2, 3)), 2, false),
                arguments(new int[][]{
                        {1, 2, 1, 0, 1},
                        {1, 1, 2, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                }, new Move(new Coordinate(2, 0), new Coordinate(3, 0)), 1, true),
                arguments(new int[][]{
                        {1, 1, 1, 1, 1},
                        {1, 1, 2, 1, 1},
                        {1, 0, 1, 0, 1},
                        {1, 1, 0, 1, 1},
                        {1, 1, 1, 1, 1},
                }, new Move(new Coordinate(2, 2), new Coordinate(2, 1)), 1, false),
                arguments(new int[][]{
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 2, 1},
                        {1, 1, 1, 1, 1},
                }, new Move(new Coordinate(3, 3), new Coordinate(1, 3)), 2, false)
        );
    }
}
