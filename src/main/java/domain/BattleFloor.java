package domain;


import org.apache.commons.lang3.StringUtils;
import utils.PositionHelper;

import java.util.List;

import static java.lang.System.out;

public class BattleFloor {
    private int[][] area;

    public BattleFloor(Dimension dimension, List<Ship> ships) {
        this.area = new int[dimension.WIDTH][dimension.HEIGHT];
        ships.stream().forEach(this::positionShipToFloor);
    }

    private void positionShipToFloor(Ship ship) {
        int shipHeight, shipWidth;
        shipHeight = shipWidth = 0;
        do {
            this.area[ship.initialPosition.xCoord][ship.initialPosition.yCoord + shipHeight] = ship.shipType.weight;
            shipHeight++;
        } while (shipHeight < ship.dim.HEIGHT);
        do {
            this.area[ship.initialPosition.xCoord + shipWidth][ship.initialPosition.yCoord] = ship.shipType.weight;
            shipWidth++;
        } while (shipWidth < ship.dim.WIDTH);
    }

    public boolean attackAt(String stringPosition, int playerId) {
        if (StringUtils.isEmpty(stringPosition)) {
            System.out.println("Player-" + playerId + " has no more missiles left to launch");
            return false;
        }
        Position position = PositionHelper.toBedCooridinates(stringPosition);
        int positionWeight = this.area[position.xCoord][position.yCoord];
        if (positionWeight > 0) {
            this.area[position.xCoord][position.yCoord] = positionWeight - 1;
            out.println("Player-" + playerId + " fires a missile with target " + stringPosition + " which got hit");
            return true;
        }
        out.println("Player-" + playerId + " fires a missile with target " + stringPosition + " which got miss");
        return false;
    }

    public int getPositionWeight(int x, int y) {
        return this.area[x][y];
    }

    boolean completlyDestroyed() {
        int temp = 0;
        for (int i = 0; i < this.area.length; i++) {
            for (int j = 0; j < this.area[0].length; j++) {
                temp += this.area[i][j];
            }
        }
        return temp == 0;
    }
}
