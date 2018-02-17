import org.junit.Test;
import utils.ShipHelper;

import static org.junit.Assert.assertEquals;

public class ShipSpecs {

    @Test
    public void correctlyParseShipForPlayerA1() {
        assertEquals(ShipHelper.parseShipDetails("Q 1 1 A1 B2", 1).toString(), "1X1-00-Q");
    }

    @Test
    public void correctlyParseShipForPlayerB1() {
        assertEquals(ShipHelper.parseShipDetails("P 1 1 A1 B2", 2).toString(), "1X1-11-P");
    }

    @Test
    public void correctlyParseShipForPlayerA2() {
        assertEquals(ShipHelper.parseShipDetails("Q 4 2 Z9 B2", 1).toString(), "2X4-258-Q");
    }

    @Test
    public void correctlyParseShipForPlayerB2() {
        assertEquals(ShipHelper.parseShipDetails("P 7 4 A1 B2", 2).toString(), "4X7-11-P");
    }
}
