package solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Utils {

  public static Map<String, String> getKingdoms() {
    final Map<String, String> kingdoms = new HashMap<>();
    kingdoms.put("land", "panda");
    kingdoms.put("water", "octopus");
    kingdoms.put("ice", "mammoth");
    kingdoms.put("air", "owl");
    kingdoms.put("fire", "dragon");
    kingdoms.put("space", "gorilla");
    return kingdoms;
  }

  public List<String> readInput(int n) throws IOException {
    List<String> inputString = new ArrayList<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    while (n > 0) {
      inputString.add(br.readLine().toLowerCase());
      n--;
    }
    return inputString;
  }

  public String[][] cleanInput(List<String> noise) {
    String[][] hold = new String[noise.size()][2];
    int i = 0;
    for (String str : noise) {
      hold[i++] = str.split(",\\s*\"");
    }
    return hold;
  }

  public List<String> getAllies(String[][] index, Map<String, String> kingdoms) {
    List<String> allies = new ArrayList<>();
    for (int i = 0; i < index.length; i++) {
      String emblem = kingdoms.get(index[i][0]);
      if (parseString(index[i][1], emblem)) {
        allies.add(index[i][0]);
      }
    }
    return allies;
  }

  public boolean parseString(String toParse, String emblem) {
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


}
