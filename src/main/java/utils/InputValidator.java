package utils;

import domain.Dimension;
import domain.Ship;
import org.apache.commons.lang3.Range;

public class InputValidator {
    public static void validateBattleGroundDimension(Dimension dimension) {
        if (isOutsideRange(1, 9, dimension.WIDTH) ||
                isOutsideRange(1, 25, dimension.HEIGHT)) {
            throw new IllegalArgumentException("Dimensions out of range");
        }
    }

    public static void validateNoOfBattleShips(int noOfBattleShips, Dimension dimension) {
        if (isOutsideRange(1, dimension.WIDTH * dimension.HEIGHT, noOfBattleShips)) {
            throw new IllegalArgumentException("No of battleships out of range");
        }
    }

    public static void validateShip(Ship aShip, Dimension battleGroundDim) {
        if (isOutsideRange(1, battleGroundDim.WIDTH, aShip.dim.WIDTH) ||
                isOutsideRange(1, battleGroundDim.HEIGHT, aShip.dim.HEIGHT)) {
            throw new IllegalArgumentException("Battleship dimension out of range");
        }

        if (isOutsideRange(0, battleGroundDim.WIDTH - 1, aShip.initialPosition.xCoord) ||
                isOutsideRange(0, battleGroundDim.HEIGHT - 1, aShip.initialPosition.yCoord)) {
            throw new IllegalArgumentException("Battleship position out of range");
        }

    }

    private static boolean isOutsideRange(Integer start, Integer end, int testValue) {
        return !Range.between(start, end).contains(testValue);
    }
}