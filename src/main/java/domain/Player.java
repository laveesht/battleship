package domain;

import java.util.List;

public class Player {
    public final int playerId;
    private final BattleFloor battleFloor;
    private final List<String> missilePositions;
    private int missilePositionIndex = 0;

    public Player(int playerId, Dimension dim, List<Ship> ships, List<String> missilePositions) {
        this.playerId = playerId;
        this.battleFloor = new BattleFloor(dim, ships);
        this.missilePositions = missilePositions;
    }

    public String getNextAttackPosition() {
        if (!anyMissilesLeft()) {
            return "";
        }
        String attackPosition = this.missilePositions.get(missilePositionIndex);
        missilePositionIndex++;
        return attackPosition;
    }

    public boolean anyMissilesLeft() {
        return this.missilePositionIndex < missilePositions.size();
    }

    public boolean receiveAttack(String nextAttackPosition, int playerId) {
        return this.battleFloor.attackAt(nextAttackPosition, playerId);
    }

    public boolean lost() {
        return this.battleFloor.completlyDestroyed();
    }
}
