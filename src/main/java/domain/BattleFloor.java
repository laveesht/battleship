package domain;


import static java.lang.System.out;

public class BattleFloor {
    public int[][] area;
    public final int playerId;
    public int missilesLeft;

    public BattleFloor(Dimension dimension, int playerId, int missilesLeft) {
        this.area = new int[dimension.WIDTH][dimension.HEIGHT];
        this.playerId = playerId;
        this.missilesLeft = missilesLeft;
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

    public boolean attackAt(Position position, int playerId) {
        int positionWeight = this.area[position.xCoord][position.yCoord];
        if (positionWeight > 0) {
            this.area[position.xCoord][position.yCoord] = positionWeight - 1;
            System.out.println("Player-" + playerId + " fires a missile with target " + position.toString() + " which got hit");
            return true;
        }
        System.out.println("Player-" + playerId + " fires a missile with target " + position.toString() + " which got miss");
        return false;
    }

    public boolean completlyDestroyed() {
        int temp = 0;
        for (int i = 0; i < this.area.length; i++) {
            for (int j = 0; j < this.area[0].length; j++) {
                temp += this.area[i][j];
            }
        }
        return temp == 0;
    }
}
