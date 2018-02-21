package domain;


import org.apache.commons.lang3.StringUtils;
import utils.PositionHelper;

import java.util.List;
import java.util.stream.IntStream;

import static java.lang.System.out;

public class BattleFloor {
    private int[][] area;

    public BattleFloor(Dimension dimension, List<BattleShip> battleShips) {
        this.area = new int[dimension.WIDTH][dimension.HEIGHT];
        battleShips.stream().forEach(this::positionShip);
    }

    private void positionShip(BattleShip battleShip) {
        IntStream.range(0, battleShip.dim.HEIGHT).forEach(index -> {
            this.area[battleShip.coord.x][battleShip.coord.y + index] = battleShip.shipType.weight;
        });
        IntStream.range(0, battleShip.dim.WIDTH).forEach(index -> {
            this.area[battleShip.coord.x + index][battleShip.coord.y] = battleShip.shipType.weight;
        });
    }

    public boolean attackAt(String stringPosition, int playerId) {
        if (StringUtils.isEmpty(stringPosition)) {
            System.out.println("Player-" + playerId + " has no more missiles left to launch");
            return false;
        }
        Coordinates pos = PositionHelper.toFloorCooridinates(stringPosition);
        int positionWeight = this.area[pos.x][pos.y];
        if (positionWeight > 0) {
            this.area[pos.x][pos.y] = positionWeight - 1;
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
