import domain.BattleFloor;
import domain.Dimension;
import utils.PositionHelper;

import static utils.ShipHelper.parseShipDetails;

public class Starter {
    public final int NO_OF_MISSILES = 10;


    public static void main(String args[]) {
        String inputLine1 = "5 E";
        Dimension battleGroundDim = PositionHelper.toAreaDimensions(inputLine1);
        BattleFloor playerABed = new BattleFloor(battleGroundDim, 1);
        BattleFloor playerBBed = new BattleFloor(battleGroundDim, 2);

        //No of battleships
        String inputLine2 = "2";
        int noOfBattleShips = Integer.parseInt(inputLine2);


        //BattleShip Positions
        String inputLine3 = "Q 1 1 A1 B2";
        playerABed.positionShipToFloor(parseShipDetails(inputLine3, 1));
        playerBBed.positionShipToFloor(parseShipDetails(inputLine3, 2));

        String inputLine4 = "P 2 1 D4 C3";
        playerABed.positionShipToFloor(parseShipDetails(inputLine4, 1));
        playerBBed.positionShipToFloor(parseShipDetails(inputLine4, 2));

        //Attack Positions
        String inputLine5 = "A1 B2 B2 B3";
        String inputLine6 = "A1 B2 B3 A1 D1 E1 D4 D4 D5 D5";


        playerABed.displayBattleFloor();
        playerBBed.displayBattleFloor();
    }

}

