package domain;

import java.util.List;

public class Player {
    public final int playerId;
    private List<String> attackPositions;
    private int attackPositionIndex = 0;

    public Player(int playerId) {
        this.playerId = playerId;
    }

    public String getNextAttackPosition() {
        if (!anyMissilesLeft()) {
            return "";
        }
        String attackPosition = this.attackPositions.get(attackPositionIndex);
        attackPositionIndex++;
        return attackPosition;
    }

    public boolean anyMissilesLeft() {
        return this.attackPositionIndex <= attackPositions.size() - 1;
    }

    public void setAttackPositions(List<String> attackPositions) {
        this.attackPositions = attackPositions;
    }
}
