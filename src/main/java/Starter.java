import domain.BattleFloor;
import domain.Dimension;
import domain.Player;
import domain.Position;
import utils.PositionHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.System.out;
import static java.util.stream.Collectors.toList;
import static utils.PositionHelper.toListOfAttackPositions;
import static utils.ShipHelper.parseShipDetails;

public class Starter {
    public static int activePlayer = 1; //Assuming player1 takes first turn

    public static void main(String args[]) throws IOException {
        String inputFilePath = args.length > 0 ? args[0] : "src/main/resources/input.txt";

        List<String> lines = Files.lines(Paths.get(inputFilePath)).collect(toList());

        //BattleGround dimensions
        String inputLine1 = lines.get(0);
        Dimension battleGroundDim = PositionHelper.toAreaDimensions(inputLine1);

        BattleFloor playerABed = new BattleFloor(battleGroundDim);
        BattleFloor playerBBed = new BattleFloor(battleGroundDim);

        //No of battleships
        String inputLine2 = lines.get(1);
        int noOfBattleShips = Integer.parseInt(inputLine2);

        //Position Battleships
        for (int i = 0; i < noOfBattleShips; i++) {
            playerABed.positionShipToFloor(parseShipDetails(lines.get(2 + i), 1));
            playerBBed.positionShipToFloor(parseShipDetails(lines.get(2 + i), 2));
        }

        //Player Attack Positions
        String inputLine5 = lines.get(2 + noOfBattleShips);
        String inputLine6 = lines.get(3 + noOfBattleShips);

        List<String> playerAAttackPositions = toListOfAttackPositions(inputLine5);
        List<String> playerBAttackPositions = toListOfAttackPositions(inputLine6);


        Player playerA = new Player(1, playerAAttackPositions);
        Player playerB = new Player(2, playerBAttackPositions);

        //Shooting Begins
        while (eitherPlayerBattleShipsSurvives(playerABed, playerBBed) && missilesLeftForAnyPlayer(playerA, playerB)) {
            boolean hit;
            if (activePlayer == 1) {
                hit = playerA.shoot(playerBBed);
            } else {
                hit = playerB.shoot(playerABed);
            }
            computeActivePlayer(hit);
        }

        if (doWeHaveAChampion(playerABed, playerBBed)) {
            out.println("Player " + activePlayer + " won the battle");
        } else {
            out.println("Peace begins...");
        }
    }

    private static void computeActivePlayer(boolean hit) {
        activePlayer = hit ? activePlayer : ((activePlayer == 1) ? 2 : 1);
    }

    private static boolean eitherPlayerBattleShipsSurvives(BattleFloor A, BattleFloor B) {
        return !doWeHaveAChampion(A, B);
    }

    private static boolean doWeHaveAChampion(BattleFloor A, BattleFloor B) {
        return A.completlyDestroyed() || B.completlyDestroyed();
    }

    private static boolean missilesLeftForAnyPlayer(Player playerA, Player playerB) {
        return playerA.anyMissilesLeft() || playerB.anyMissilesLeft();
    }

}

