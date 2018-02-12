package solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConquerTheThrone {

  private static final Logger LOGGER = LoggerFactory.getLogger(ConquerTheThrone.class);

  public static List<String> readInput(int n) throws IOException {
    List<String> inputString = new ArrayList<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while (n > 0) {
      inputString.add(br.readLine().toLowerCase());
      n--;
    }
    return inputString;
  }

  public static String[][] cleanInput(List<String> noise) {
    String[][] hold = new String[noise.size()][2];
    int i = 0;
    for (String str : noise) {
      hold[i++] = str.split(",\\s*\"");
    }
    return hold;
  }

  public static List<String> getAllies(String[][] index, Map<String, String> kingdoms) {
    List<String> allies = new ArrayList<>();
    for (int i = 0; i < index.length; i++) {
      String emblem = kingdoms.get(index[i][0]);
      if (parseString(index[i][1], emblem)) {
        allies.add(index[i][0]);
      }
    }
    return allies;
  }

  public static boolean parseString(String toParse, String emblem) {
    char[] check = emblem.toCharArray();
    for (char toTest : check) {
      if (toParse.indexOf(toTest) > -1) {
        toParse.replaceFirst(String.valueOf(toTest), "");
      } else {
        return false;
      }
    }
    return true;

  }

  public ConquerTheThrone() throws IOException {
    boolean victory = false;
    String[][] input = cleanInput(readInput(3));
    List<String> allies = getAllies(input, Utils.getKingdoms());
    if (allies.size() >= 3) {
      victory = true;
    }
    System.out.println(victory);
    LOGGER.info("Who is the ruler of Southeros");
    if (victory) {
      LOGGER.info("King Shan");
    } else {
      LOGGER.info("None");
    }
    LOGGER.info("Allies of the ruler");
    if (victory) {
      for (String str : allies) {
        LOGGER.info("{}", str);
      }
    } else {
      LOGGER.info("None");
    }
  }

  public static void main(String[] args) throws IOException {
    // new ConquerTheThrone();
    BreakerOfChainsImpl bc = new BreakerOfChainsImpl();
    bc.pickCompetitorKingdoms(6);
  }
}
