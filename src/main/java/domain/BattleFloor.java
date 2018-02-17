package domain;


import static java.lang.System.out;

public class BattleFloor {
    public int[][] area;
    public final int playerId;

    public BattleFloor(Dimension dimension, int playerId) {
        this.area = new int[dimension.WIDTH][dimension.HEIGHT];
        this.playerId = playerId;
    }

    public void positionShipToFloor(Ship ship) {
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

    public void displayBattleFloor() {
        out.println("");
        for (int i = 0; i < this.area.length; i++) {
            out.println();
            for (int j = 0; j < this.area[0].length; j++) {
                out.print(this.area[i][j]);
            }
        }
    }

    public void attack(Position position) {

    }

    public boolean completlyDestroyed() {
        return false;
    }
}
