package utils;

import domain.Dimension;
import domain.BattleShip;
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

    public static void validateShip(BattleShip aBattleShip, Dimension battleGroundDim) {
        if (isOutsideRange(1, battleGroundDim.WIDTH, aBattleShip.dim.WIDTH) ||
                isOutsideRange(1, battleGroundDim.HEIGHT, aBattleShip.dim.HEIGHT)) {
            throw new IllegalArgumentException("Battleship dimension out of range");
        }

        if (isOutsideRange(0, battleGroundDim.WIDTH - 1, aBattleShip.coord.x) ||
                isOutsideRange(0, battleGroundDim.HEIGHT - 1, aBattleShip.coord.y)) {
            throw new IllegalArgumentException("Battleship position out of range");
        }

    }

    private static boolean isOutsideRange(Integer start, Integer end, int testValue) {
        return !Range.between(start, end).contains(testValue);
    }
}