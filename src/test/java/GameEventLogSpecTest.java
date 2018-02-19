import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertTrue;

public class GameEventLogSpecTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void reset() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testGameEventLogs() throws IOException {
        BattleShipStarter.main(new String[]{});
        assertTrue(verifyGameEventLogs(outContent.toString().split("\n"), "src/test/resources/input_event_log.txt"));
    }

    private boolean verifyGameEventLogs(String[] logArr, String filePath) throws IOException {
        List<String> lines = Files.lines(Paths.get(filePath)).collect(toList());
        for (int i = 0; i < logArr.length; i++) {
            if (!logArr[i].equals(lines.get(i).toString())) {
                return false;
            }
        }
        return true;
    }
}
