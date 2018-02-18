import org.junit.Test;
import utils.PositionHelper;

import static org.junit.Assert.assertEquals;

public class PositionSpecTest {

    @Test
    public void correctlyParseBedPositions1() {
        assertEquals(PositionHelper.toBedCooridinates("A3").toString(), "02");
    }

    @Test
    public void correctlyParseBedPositions2() {
        assertEquals("252", PositionHelper.toBedCooridinates("Z3").toString());
    }

    @Test
    public void correctBattleGroundDimensions() {
        assertEquals("5X5", PositionHelper.toAreaDimensions("5 E").toString());
    }

}
