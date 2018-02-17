package utils;

import domain.Dimension;
import domain.Position;
import domain.Ship;
import domain.Ship.ShipType;

import static utils.PositionHelper.toInt;

public class ShipHelper {

    public static Ship parseShipDetails(String data, int playerId) {
        // "Q 1 1 A1 B2"
        String[] temp = data.split(" ");
        ShipType shipType = ShipType.findByName(temp[0]);
        Dimension dimension = new Dimension(toInt(temp[2], false), toInt(temp[1], false));
        Position initialPosition = playerId == 1 ? PositionHelper.toBedCooridinates(temp[3]) : PositionHelper.toBedCooridinates(temp[4]);
        return new Ship(dimension, initialPosition, shipType);
    }
}
