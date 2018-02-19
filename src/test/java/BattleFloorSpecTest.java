import domain.BattleFloor;
import domain.Dimension;
import domain.Coordinates;
import domain.BattleShip;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static domain.BattleShip.ShipType.P;
import static domain.BattleShip.ShipType.Q;
import static org.junit.Assert.*;

public class BattleFloorSpecTest {
    private BattleFloor battleFloor;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void resetFloor() {
        BattleShip battleShip1 = new BattleShip(new Dimension(2, 1), new Coordinates(0, 1), P);
        BattleShip battleShip2 = new BattleShip(new Dimension(2, 1), new Coordinates(3, 4), Q);
        battleFloor = new BattleFloor(new Dimension(5, 5), Arrays.asList(battleShip1, battleShip2));
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void correctlyPositionShip() {
        assertTrue(battleFloor.getPositionWeight(0, 1) > 0);
        assertTrue(battleFloor.getPositionWeight(1, 1) > 0);
    }

    @Test
    public void correctlyAssignsShipWeight() {
        assertEquals(1, battleFloor.getPositionWeight(0, 1));
        assertEquals(2, battleFloor.getPositionWeight(3, 4));
        assertEquals(2, battleFloor.getPositionWeight(4, 4));
    }

    @Test
    public void hitGenratesCorrectPlayerEvents() {
        assertEquals(1, battleFloor.getPositionWeight(0, 1));
        assertTrue(battleFloor.attackAt("12", 1));
        assertEquals("Player-1 fires a missile with target 12 which got hit", outContent.toString().trim());
        assertEquals(0, battleFloor.getPositionWeight(0, 1));
    }

    @Test
    public void missGenratesCorrectPlayerEvents() {
        assertEquals(1, battleFloor.getPositionWeight(0, 1));
        assertFalse(battleFloor.attackAt("21", 1));
        assertEquals("Player-1 fires a missile with target 21 which got miss", outContent.toString().trim());
    }

    @Test
    public void noMissileGenratesCorrectPlayerEvents() {
        assertEquals(1, battleFloor.getPositionWeight(0, 1));
        assertFalse(battleFloor.attackAt("", 1));
        assertEquals("Player-1 has no more missiles left to launch", outContent.toString().trim());
    }
}
