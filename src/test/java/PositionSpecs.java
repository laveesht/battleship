import org.junit.Test;
import utils.PositionHelper;

import static org.junit.Assert.assertEquals;

public class PositionSpecs {

    @Test
    public void correctlyParseBedPositions1() {
        assertEquals(PositionHelper.toBedCooridinates("A3").toString(), "02");
    }

    @Test
    public void correctlyParseBedPositions2() {
        assertEquals(PositionHelper.toBedCooridinates("Z3").toString(), "252");
    }

    @Test
    public void correctBattleGroundDimensions() {
        assertEquals(PositionHelper.toAreaDimensions("5 E").toString(), "5X5");
    }

}
