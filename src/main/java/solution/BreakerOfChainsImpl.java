package solution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dto.MessageDTO;


public class BreakerOfChainsImpl implements BreakerOfChains {
  private static final Logger LOGGER = LoggerFactory.getLogger(BreakerOfChainsImpl.class);

  @Override
  public List<String> pickCompetitorKingdoms(int n) {
    Random arya = new Random();
    int kingdomsPart = arya.nextInt(n) + 1;
    LOGGER.debug("Number of competitors{}", kingdomsPart);
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
  public List<MessageDTO> generateMessages(List<String> comp) {
    List<MessageDTO> message = new ArrayList<>();
    List<String> result = new ArrayList<>();
    for (String king : comp) {
      MessageDTO m = new MessageDTO();
      m.setFrom(king);
      m.setTo("");
      m.setMessage(Utils.getRandomMessage(Utils.getMessages()));
    }
    return null;
  }
}
