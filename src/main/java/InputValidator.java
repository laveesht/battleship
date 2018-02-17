import domain.Dimension;
import domain.Ship;
import org.apache.commons.lang3.Range;

public class InputValidator {
    public static void validateBattleGroundDimension(Dimension dimension) {
        if (!(Range.between(1, 9).contains(dimension.WIDTH) && Range.between(1, 25).contains(dimension.HEIGHT))) {
            throw new IllegalArgumentException("Dimensions out of range");
        }
    }

    public static void validateNoOfBattleShips(int noOfBattleShips, Dimension dimension) {
        if (!(Range.between(1, dimension.WIDTH * dimension.HEIGHT).contains(noOfBattleShips))) {
            throw new IllegalArgumentException("No of battleships out of range");
        }
    }

    public static void validateShip(Ship aShip, Dimension battleGroundDim) {
        if (!(Range.between(1, battleGroundDim.WIDTH).contains(aShip.dim.WIDTH) &&
                Range.between(1, battleGroundDim.HEIGHT).contains(aShip.dim.HEIGHT))) {
            throw new IllegalArgumentException("Battleship dimension out of range");
        }

        if (!(Range.between(0, battleGroundDim.WIDTH - 1).contains(aShip.initialPosition.xCoord) &&
                Range.between(0, battleGroundDim.HEIGHT - 1).contains(aShip.initialPosition.yCoord))) {
            throw new IllegalArgumentException("Battleship position out of range");
        }

    }
}