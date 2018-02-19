package domain;

public class BattleShip {
    final public Dimension dim;
    final public Coordinates coord;
    final public ShipType shipType;

    public BattleShip(Dimension dim, Coordinates coord, ShipType shipType) {
        this.dim = dim;
        this.coord = coord;
        this.shipType = shipType;
    }

    @Override
    public String toString() {
        return dim + "-" + coord + "-" + shipType;
    }

    public enum ShipType {
        P(1), Q(2);

        public final int weight;

        ShipType(int weight) {
            this.weight = weight;
        }

        public static ShipType findByName(String type) {
            for (ShipType t : ShipType.values()) {
                if (t.name().toString().equals(type)) {
                    return t;
                }
            }
            throw new IllegalArgumentException("Ship type invalid");
        }

    }
}

