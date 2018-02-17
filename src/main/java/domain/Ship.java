package domain;

public class Ship {
    final Dimension dim;
    final Position initialPosition;
    final ShipType shipType;

    public Ship(Dimension dim, Position initialPosition, ShipType shipType) {
        this.dim = dim;
        this.initialPosition = initialPosition;
        this.shipType = shipType;
    }

    @Override
    public String toString() {
        return dim + "-" + initialPosition + "-" + shipType;
    }

    public enum ShipType {
        P(1), Q(2);

        public int weight;

        ShipType(int weight) {
            this.weight = weight;
        }

        public static ShipType findByName(String type) {
            for (ShipType t : ShipType.values()) {
                if (t.name().toString().equals(type)) {
                    return t;
                }
            }
            throw new IllegalArgumentException();
        }

    }
}

