import domain.Dimension;
import domain.Player;
import domain.BattleShip;
import utils.InputValidator;
import utils.PositionHelper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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

        //No of battleships
        String inputLine2 = lines.get(1);
        int noOfBattleShips = Integer.parseInt(inputLine2);
        InputValidator.validateNoOfBattleShips(noOfBattleShips, battleGroundDim);

        List<BattleShip> playerABattleShips = new ArrayList();
        List<BattleShip> playerBBattleShips = new ArrayList();

        //Parse input and position battleships for both players
        for (int i = 0; i < noOfBattleShips; i++) {
            BattleShip aBattleShip = parseShipDetails(lines.get(2 + i), 3);
            BattleShip bBattleShip = parseShipDetails(lines.get(2 + i), 4);
            InputValidator.validateShip(aBattleShip, battleGroundDim);
            InputValidator.validateShip(bBattleShip, battleGroundDim);
            playerABattleShips.add(aBattleShip);
            playerBBattleShips.add(bBattleShip);
        }

        //Load player attack positions
        String playerAAttackCoordinates = lines.get(2 + noOfBattleShips);
        String playerBAttackCoordinates = lines.get(3 + noOfBattleShips);
        List<String> attackAPositions = PositionHelper.convertToList(playerAAttackCoordinates);
        List<String> attackBPositions = PositionHelper.convertToList(playerBAttackCoordinates);

        //Initialize player
        Player playerA = new Player(1, battleGroundDim, playerABattleShips, attackAPositions);
        Player playerB = new Player(2, battleGroundDim, playerBBattleShips, attackBPositions);

        //Shooting Begins
        while (eitherPlayerBattleShipsSurvives(playerA, playerB) && missilesLeftForAnyPlayer(playerA, playerB)) {
            boolean hit;
            if (activePlayer == 1) {
                hit = playerB.receiveAttack(playerA.getNextAttackPosition(), playerA.playerId);
            } else {
                hit = playerA.receiveAttack(playerB.getNextAttackPosition(), playerB.playerId);
            }
            computeActivePlayer(hit);
        }

        if (doWeHaveAChampion(playerA, playerB)) {
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

    private static boolean eitherPlayerBattleShipsSurvives(Player playerA, Player playerB) {
        return !doWeHaveAChampion(playerA, playerB);
    }

    private static boolean doWeHaveAChampion(Player playerA, Player playerB) {
        return playerA.lost() || playerB.lost();
    }

    private static boolean missilesLeftForAnyPlayer(Player playerA, Player playerB) {
        return playerA.anyMissilesLeft() || playerB.anyMissilesLeft();
    }

}