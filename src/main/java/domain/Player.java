package domain;

import java.util.List;

public class Player {
    public final int playerId;
    public final List<String> attackPositions;
    private int attackPositionIndex = 0;

    public Player(int playerId, List<String> attackPositions) {
        this.playerId = playerId;
        this.attackPositions = attackPositions;
    }

    public boolean shoot(BattleFloor playerBBed) {
        if (!anyMissilesLeft()) {
            System.out.println("Player-" + playerId + " has no more missiles left to launch");
            return false;
        }
        String attackPosition = this.attackPositions.get(attackPositionIndex);
        attackPositionIndex++;
        return playerBBed.attackAt(attackPosition, playerId);
    }

    public boolean anyMissilesLeft() {
        return this.attackPositionIndex <= attackPositions.size() - 1;
    }
}
