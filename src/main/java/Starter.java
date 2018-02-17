import domain.BattleFloor;
import domain.Dimension;
import domain.Position;
import utils.PositionHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

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
        //No of battleships
        String inputLine2 = lines.get(1);
        int noOfBattleShips = Integer.parseInt(inputLine2);

        //BattleShip Positions
        String inputLine3 = "Q 1 1 A1 B2";
        String inputLine4 = "P 2 1 D4 C3";

        Dimension battleGroundDim = PositionHelper.toAreaDimensions(inputLine1);

        //Attack Positions
        String inputLine5 = "A1 B2 B2 B3";
        String inputLine6 = "A1 B2 B3 A1 D1 E1 D4 D4 D5 D5";


        List<Position> playerAAttackPositions = toListOfAttackPositions(inputLine5);
        List<Position> playerBAttackPositions = toListOfAttackPositions(inputLine6);


        BattleFloor playerABed = new BattleFloor(battleGroundDim, 1, playerAAttackPositions.size());
        BattleFloor playerBBed = new BattleFloor(battleGroundDim, 2, playerBAttackPositions.size());

        playerABed.positionShipToFloor(parseShipDetails(inputLine3, 1));
        playerBBed.positionShipToFloor(parseShipDetails(inputLine3, 2));

        playerABed.positionShipToFloor(parseShipDetails(inputLine4, 1));
        playerBBed.positionShipToFloor(parseShipDetails(inputLine4, 2));


        //Shooting Begins
        int playerAPositionIndex = 0;
        int playerBPositionIndex = 0;
        while (playContinues(playerABed, playerBBed)) {
            boolean hit;
            if (activePlayer == 1) {
                if (playerABed.missilesLeft <= 0) {
                    System.out.println("Player-1 has no more missiles left to launch");
                    hit = false;
                } else {
                    hit = playerAShoots(playerBBed, playerAAttackPositions.get(playerAPositionIndex));
                    playerABed.missilesLeft--;
                    playerAPositionIndex++;
                }
            } else {
                if (playerBBed.missilesLeft <= 0) {
                    System.out.println("Player-2 has no more missiles left to launch");
                    hit = false;
                } else {
                    hit = playerBShoots(playerABed, playerBAttackPositions.get(playerBPositionIndex));
                    playerBBed.missilesLeft--;
                    playerBPositionIndex++;
                }
            }
            computerActivePlayer(hit);
        }

        if (doWeHaveAChampion(playerABed, playerBBed)) {
            System.out.println("Player " + activePlayer + " won the battle");
        } else {
            System.out.println("Peace begins...");
        }
    }

    private static void computerActivePlayer(boolean hit) {
        activePlayer = hit ? activePlayer : ((activePlayer == 1) ? 2 : 1);
    }

    private static boolean playerAShoots(BattleFloor battleFloor, Position pos) {
        return battleFloor.attackAt(pos, 1);
    }

    private static boolean playerBShoots(BattleFloor battleFloor, Position pos) {
        return battleFloor.attackAt(pos, 2);
    }

    private static boolean playContinues(BattleFloor A, BattleFloor B) {
        return eitherPlayerBattleShipsSurvives(A, B) && missilesLeftForAnyPlayer(A, B);
    }

    private static boolean eitherPlayerBattleShipsSurvives(BattleFloor A, BattleFloor B) {
        return !(A.completlyDestroyed() || B.completlyDestroyed());
    }

    private static boolean doWeHaveAChampion(BattleFloor A, BattleFloor B) {
        return A.completlyDestroyed() || B.completlyDestroyed();
    }

    private static boolean missilesLeftForAnyPlayer(BattleFloor A, BattleFloor B) {
        return A.missilesLeft > 0 || B.missilesLeft > 0;
    }

}

