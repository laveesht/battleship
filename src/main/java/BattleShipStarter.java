import domain.BattleFloor;
import domain.Dimension;
import domain.Player;
import domain.Ship;
import utils.InputValidator;
import utils.PositionHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.lang.System.out;
import static java.util.stream.Collectors.toList;
import static utils.ShipHelper.parseShipDetails;

/*
 * Assumptions -
 * 1. Twin player game
 * 1. player1 takes first turn
 * 2. player #missiles = no of attack positions
 *
 * Input -
 * 1. Input file path should be passed as command line params
 * */

public class BattleShipStarter {
    private static int activePlayer = 1;

    public static void main(String args[]) throws IOException {
        String inputFilePath = args.length > 0 ? args[0] : "src/main/resources/input.txt";

        List<String> lines = Files.lines(Paths.get(inputFilePath)).collect(toList());

        //BattleGround dimensions
        String inputLine1 = lines.get(0);
        Dimension battleGroundDim = PositionHelper.toAreaDimensions(inputLine1);
        InputValidator.validateBattleGroundDimension(battleGroundDim);

        //Initialize player
        Player playerA = new Player(1);
        Player playerB = new Player(2);

        //Initialize players battlefloor
        BattleFloor playerABed = new BattleFloor(battleGroundDim, playerA.playerId);
        BattleFloor playerBBed = new BattleFloor(battleGroundDim, playerB.playerId);

        //No of battleships
        String inputLine2 = lines.get(1);
        int noOfBattleShips = Integer.parseInt(inputLine2);
        InputValidator.validateNoOfBattleShips(noOfBattleShips, battleGroundDim);


        //Parse input and position battleships for both players
        for (int i = 0; i < noOfBattleShips; i++) {
            Ship aShip = parseShipDetails(lines.get(2 + i), playerA.playerId);
            Ship bShip = parseShipDetails(lines.get(2 + i), playerB.playerId);
            InputValidator.validateShip(aShip, battleGroundDim);
            InputValidator.validateShip(bShip, battleGroundDim);
            playerABed.positionShipToFloor(aShip);
            playerBBed.positionShipToFloor(bShip);
        }

        //Load player attack positions
        String playerAAttackCoordinates = lines.get(2 + noOfBattleShips);
        String playerBAttackCoordinates = lines.get(3 + noOfBattleShips);
        playerA.setAttackPositions(PositionHelper.convertToList(playerAAttackCoordinates));
        playerB.setAttackPositions(PositionHelper.convertToList(playerBAttackCoordinates));

        //Shooting Begins
        while (eitherPlayerBattleShipsSurvives(playerABed, playerBBed) && missilesLeftForAnyPlayer(playerA, playerB)) {
            boolean hit;
            if (activePlayer == 1) {
                hit = playerBBed.attackAt(playerA.getNextAttackPosition(), playerA.playerId);
            } else {
                hit = playerABed.attackAt(playerB.getNextAttackPosition(), playerB.playerId);
            }
            computeActivePlayer(hit);
        }

        if (doWeHaveAChampion(playerABed, playerBBed)) {
            out.println("Player-" + activePlayer + " won the battle");
        } else {
            out.println("Peace begins...");
        }
    }

    private static void computeActivePlayer(boolean hit) {
        if (!hit) {
            activePlayer = (activePlayer == 1) ? 2 : 1;
        }
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