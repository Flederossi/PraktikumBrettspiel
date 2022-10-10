import com.flederossi.game.Board;
import com.flederossi.game.Move;
import com.flederossi.players.AI;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class AIBlackLogicTest {
    private final AI ai;

    public AIBlackLogicTest(){
        this.ai = new AI();
    }

    @ParameterizedTest
    @MethodSource("testCases")
    public void testAIOutput(Board board, int[] expected){
        Move move = this.ai.generateNextMove(2, board);
        int[] res = {move.startPos.x, move.startPos.y, move.endPos.x, move.endPos.y};
        assertArrayEquals(expected, res);
    }

    private static Stream<Arguments> testCases(){
        return Stream.of(
                arguments(new Board(new int[][]{
                        {1, 2, 0, 1, 1},
                        {2, 0, 0, 1, 1},
                        {2, 0, 0, 1, 1},
                        {0, 1, 1, 1, 1},
                        {0, 1, 1, 1, 1},
                }), new int[]{0, 1, 0, 0}),
                arguments(new Board(new int[][]{
                        {1, 1, 1, 1, 0},
                        {1, 0, 1, 0, 2},
                        {0, 2, 0, 1, 0},
                        {1, 0, 1, 1, 0},
                        {1, 1, 1, 2, 1},
                }), new int[]{3, 4, 4, 4}),
                arguments(new Board(new int[][]{
                        {1, 1, 1, 2, 0},
                        {1, 1, 0, 0, 1},
                        {1, 1, 2, 0, 1},
                        {1, 1, 0, 1, 0},
                        {1, 1, 1, 2, 1},
                }), new int[]{3, 4, 4, 4}),
                arguments(new Board(new int[][]{
                        {1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1},
                        {1, 1, 0, 1, 1},
                        {0, 0, 2, 0, 0},
                        {2, 0, 1, 0, 2},
                }), new int[]{2, 3, 2, 4}),
                arguments(new Board(new int[][]{
                        {1, 1, 1, 1, 2},
                        {1, 1, 1, 1, 1},
                        {1, 1, 2, 1, 1},
                        {1, 1, 1, 1, 1},
                        {2, 1, 1, 1, 1},
                }), new int[]{4, 0, 4, 1})
        );
    }
}
