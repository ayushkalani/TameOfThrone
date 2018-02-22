package solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dto.MessageDTO;


public class BreakerOfChainsImpl implements BreakerOfChains {
  private static final Logger LOGGER = LoggerFactory.getLogger(BreakerOfChainsImpl.class);

  /*
   * n is the total number of kingdoms
   */
  @Override
  public List<String> pickCompetitorKingdoms(int n) {
    Random arya = new Random();
    int kingdomsPart = arya.nextInt(n) + 1;
    if (kingdomsPart == 1 || kingdomsPart == 6)
      kingdomsPart=3;
    LOGGER.info("Number of competitors{} ", kingdomsPart);
    Map<String, String> states = Utils.getKingdoms();
    List<String> keysAsArray = new ArrayList<>(states.keySet());
    List<String> result = new ArrayList<>();
    ArrayList<Integer> list = new ArrayList<>();
    for (int i = 0; i < kingdomsPart; i++) {
      list.add(i);
    }
    Collections.shuffle(list);
    int index = 0;
    while (kingdomsPart > 0) {
      result.add(keysAsArray.get(list.get(index++)));
      kingdomsPart--;
    }
    return result;
  }

  @Override
  public List<MessageDTO> generateMessages(List<String> competitor) {
    List<MessageDTO> message = new ArrayList<>();
    List<String> totalKingdoms = new ArrayList<>(Utils.getKingdoms().keySet());

    for (String king : competitor) {
      int count = 0;
      List<String> tempHold = new ArrayList<>(totalKingdoms);

      for (String remove : tempHold) {
        if (remove.equals(king)) {
          tempHold.remove(count);
          break;
        }
        count++;
      }
      for (String allies : tempHold) {
        MessageDTO m = new MessageDTO();
        m.setFrom(king);
        m.setTo(allies);
        m.setMessage(Utils.pickRandomFromList(Utils.getMessages()));
        message.add(m);
      }
    }
    return message;
  }

  /*
   * @param n - number of messages to be picked from the ballot
   */
  public List<MessageDTO> priestBallotPick(List<MessageDTO> ballot) {
    int n = 6;
    Map<MessageDTO, Integer> pick = new HashMap<>();
    while (n > 0) {
      MessageDTO m = Utils.pickRandomFromList(ballot);
      if (pick.containsKey(m)) {
        pick.put(Utils.pickRandomFromList(ballot), 1);
      } else {
        pick.put(m, 1);
      }
      n--;
    }
    return new ArrayList<>(pick.keySet());
  }

  public Map<String, String> determineAlleigance(List<MessageDTO> draft, List<String> competitor) {
    Map<String, String> emblemsPair = Utils.getKingdoms();
    Map<String, String> givenAlleigance = new HashMap<>();
    for (MessageDTO message : draft) {
      if (!competingKingdomRuleChecker(message.getTo(), competitor)
          && !givenAlleigance.containsKey(message.getTo())) {
        if (Utils.parseString(message.getMessage(), emblemsPair.get(message.getTo()))) {
          givenAlleigance.put(message.getTo(), message.getFrom());
        }
      }
    }
    return givenAlleigance;
  }

  public Map<String, List<String>> determineWinner(Map<String, String> alliesMap) {
    Map<String, List<String>> result = new HashMap<>();
    for (Map.Entry<String, String> entry : alliesMap.entrySet()) {
      if (!result.containsKey(entry.getValue())) {
        List<String> temp = new ArrayList<>();
        temp.add(entry.getKey());
        result.put(entry.getValue(), temp);
      } else {
        List<String> to = result.get(entry.getValue());
        to.add(entry.getKey());
        result.put(entry.getValue(), to);
      }
    }
    Map<String, Integer> winner = new HashMap<>();
    for (Map.Entry<String, List<String>> index : result.entrySet()) {
      winner.put(index.getKey(), index.getValue().size());
      LOGGER.info("allies of {}: {}", index.getKey(), index.getValue().size());
    }
    return result;
  }

  public boolean competingKingdomRuleChecker(String check, List<String> competitor) {
    for (String s : competitor) {
      if (check.equalsIgnoreCase(s)) {
        return true;
      }
    }
    return false;
  }
}
