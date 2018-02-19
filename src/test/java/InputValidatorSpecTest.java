import domain.Dimension;
import domain.Coordinates;
import domain.BattleShip;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import utils.InputValidator;

import static domain.BattleShip.ShipType.P;

public class InputValidatorSpecTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none(); //to validate error message

    private Dimension validDimesion = new Dimension(2, 2);
    private Dimension inValidDimesion = new Dimension(0, 0);
    private BattleShip battleShip = new BattleShip(new Dimension(2, 1), new Coordinates(0, 1), P);
    private BattleShip incorrectBattleShipDimesion = new BattleShip(new Dimension(4, 1), new Coordinates(0, 1), P);
    private BattleShip incorrectBattleShipPosition = new BattleShip(new Dimension(2, 1), new Coordinates(0, 4), P);

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
        InputValidator.validateShip(battleShip, validDimesion);
    }

    @Test
    public void inCorrectBattleShipDimension() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Battleship dimension out of range");
        InputValidator.validateShip(incorrectBattleShipDimesion, validDimesion);
    }

    @Test
    public void inCorrectBattleShipPosition() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("Battleship position out of range");
        InputValidator.validateShip(incorrectBattleShipPosition, validDimesion);
    }
}
