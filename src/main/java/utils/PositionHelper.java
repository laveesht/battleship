package utils;

import domain.Dimension;
import domain.Coordinates;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PositionHelper {

    public static Coordinates toFloorCooridinates(String positions) {
        int xPos = toInt(positions.substring(0, 1), true);
        int yPos = toInt(positions.substring(1, 2), true);
        return new Coordinates(xPos, yPos);
    }

    public static int toInt(String c, boolean zerobasedIndexing) {
        int index;
        if (StringUtils.isNumeric(c)) {
            index = Integer.parseInt(c);
        } else {
            String alp = "+ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            index = alp.indexOf(c);
        }
        return zerobasedIndexing ? index - 1 : index;
    }

    public static Dimension toAreaDimensions(String dimensions) {
        int height = toInt(dimensions.replaceAll("\\s+", "").substring(0, 1), false);
        int width = toInt(dimensions.replaceAll("\\s+", "").substring(1, 2), false);
        return new Dimension(width, height);
    }

    public static List<String> convertToList(String attackString) {
        return Stream.of(attackString.split(" ")).collect(toList());
    }
}
