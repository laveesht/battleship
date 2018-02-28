package utils;

import domain.BattleShip;
import domain.Dimension;
import org.apache.commons.lang3.Range;

import java.util.List;

public class InputValidator {
    public static void validateBattleGroundDimension(Dimension dimension) {
        if (isDimensionOutsideRange(1, 9, dimension.WIDTH) ||
                isDimensionOutsideRange(1, 25, dimension.HEIGHT)) {
            throw new IllegalArgumentException("Dimensions out of range");
        }
    }

    public static void validateNoOfBattleShips(int noOfBattleShips, Dimension dimension) {
        if (isDimensionOutsideRange(1, dimension.WIDTH * dimension.HEIGHT, noOfBattleShips)) {
            throw new IllegalArgumentException("No of battleships out of range");
        }
    }


    public static void validateShips(List<BattleShip> battleShipList, Dimension battleGroundDim) {
        battleShipList.stream().forEach(battleShip -> validateShip(battleShip, battleGroundDim));
    }

    public static void validateShip(BattleShip aBattleShip, Dimension battleGroundDim) {
        validateBattleShipDimension(aBattleShip, battleGroundDim);
        validateBattleShipPosition(aBattleShip, battleGroundDim);
    }

    private static void validateBattleShipDimension(BattleShip aBattleShip, Dimension battleGroundDim) {
        if (isDimensionOutsideRange(1, battleGroundDim.WIDTH, aBattleShip.dim.WIDTH) ||
                isDimensionOutsideRange(1, battleGroundDim.HEIGHT, aBattleShip.dim.HEIGHT)) {
            throw new IllegalArgumentException("Battleship dimension out of range");
        }
    }

    private static void validateBattleShipPosition(BattleShip aBattleShip, Dimension battleGroundDim) {
        if (isDimensionOutsideRange(0, battleGroundDim.WIDTH - 1, aBattleShip.coord.x) ||
                isDimensionOutsideRange(0, battleGroundDim.HEIGHT - 1, aBattleShip.coord.y)) {
            throw new IllegalArgumentException("Battleship position out of range");
        }
    }


    private static boolean isDimensionOutsideRange(Integer start, Integer end, int testValue) {
        return !Range.between(start, end).contains(testValue);
    }
}