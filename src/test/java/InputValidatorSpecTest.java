import domain.Dimension;
import domain.Position;
import domain.Ship;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.InputValidator;

import static domain.Ship.ShipType.P;

public class InputValidatorSpecTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none(); //to validate error message

    private Dimension validDimesion = new Dimension(2, 2);
    private Dimension inValidDimesion = new Dimension(0, 0);
    private Ship ship = new Ship(new Dimension(2, 1), new Position(0, 1), P);
    private Ship incorrectShipDimesion = new Ship(new Dimension(4, 1), new Position(0, 1), P);
    private Ship incorrectShipPosition = new Ship(new Dimension(2, 1), new Position(0, 4), P);

    @Test(expected = IllegalArgumentException.class)
    public void incorrectBattleFloorDimensions() {
        InputValidator.validateBattleGroundDimension(inValidDimesion);
    }

    @Test
    public void correctNoOfBattleShips() {
        InputValidator.validateNoOfBattleShips(4, validDimesion);
    }

    @Test(expected = IllegalArgumentException.class)
    public void inCorrectNoOfBattleShips() {
        InputValidator.validateNoOfBattleShips(5, validDimesion);
    }

    @Test
    public void correctBattleShipConfigs() {
        InputValidator.validateShip(ship, validDimesion);
    }

    @Test
    public void inCorrectBattleShipDimension() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Battleship dimension out of range");
        InputValidator.validateShip(incorrectShipDimesion, validDimesion);
    }

    @Test
    public void inCorrectBattleShipPosition() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Battleship position out of range");
        InputValidator.validateShip(incorrectShipPosition, validDimesion);
    }
}
