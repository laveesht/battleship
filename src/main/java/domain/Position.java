package domain;

public class Position {
    public final int xCoord;
    public final int yCoord;

    public Position(int x, int y) {
        this.xCoord = x;
        this.yCoord = y;
    }

    @Override
    public String toString() {
        return xCoord + "" + yCoord;

    }
}
