package utils;

import domain.Dimension;
import domain.Coordinates;
import domain.BattleShip;
import domain.BattleShip.ShipType;

import static utils.PositionHelper.toInt;

public class ShipHelper {

    public static BattleShip parseShipDetails(String data, int playerIndex) {
        String[] temp = data.split(" ");
        ShipType shipType = ShipType.findByName(temp[0]);
        Dimension dimension = new Dimension(toInt(temp[2], false), toInt(temp[1], false));
        Coordinates initialCoordinates = PositionHelper.toFloorCooridinates(temp[playerIndex]);
        return new BattleShip(dimension, initialCoordinates, shipType);
    }
}
